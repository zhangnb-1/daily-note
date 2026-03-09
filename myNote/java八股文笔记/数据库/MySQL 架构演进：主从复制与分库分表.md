# MySQL 架构演进：主从复制与分库分表

随着数据量和并发量的增长，单机 MySQL 终将遇到瓶颈。架构演进通常遵循：**单机 -> 主从读写分离 -> 分库分表 (Sharding)** 的路径。本文深入解析主从原理、延迟问题以及分库分表后的核心挑战与解决方案。

## 1. 主从复制原理 (Replication)

主从复制是 MySQL 高可用和读写分离的基础。

### 1.1 核心流程
1.  **Master (主库)**：将数据变更写入 **Binlog**。
2.  **Slave (从库) I/O 线程**：连接 Master，请求 Binlog，接收到后写入本地的 **Relay Log (中继日志)**。
3.  **Slave (从库) SQL 线程**：读取 Relay Log，重放 (Replay) SQL 语句或行变更，使从库数据与主库保持一致。

```text
[Master] --(Binlog Dump)--> [Slave I/O Thread] --(Write)--> [Relay Log] --(Read/Exec)--> [Slave SQL Thread] --> [Slave Data]
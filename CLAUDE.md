# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 项目概述

这是一个个人日常笔记仓库，包含多个独立的 Maven/Java 项目和学习笔记。

### 子项目结构

| 项目 | 技术栈 | 说明 |
|------|--------|------|
| `myGithubProject` | Spring Boot 2.7.7 / Java 8 | 基础 Spring Boot 项目，用于练习 AOP |
| `myLearnProject` | Spring Boot 2.7.7 / Java 17 | 学习项目，包含 Kafka、RocketMQ、Netty、Hutool、POI、Lombok |
| `springai-project` | Spring Boot 3.4.3 / Java 17 | Spring AI Alibaba (DashScope) AI 工具项目 |
| `graph2D-tool` | 纯 Maven / Java | 2D 几何图形工具库，无 Spring 依赖 |
| `myNote/` | Markdown | 学习笔记：Java 八股文、JVM、数据库、Agent 学习路径 |

## 常用命令

### myGithubProject
```bash
cd myGithubProject
mvn clean compile        # 编译
mvn clean package       # 打包
mvn spring-boot:run     # 运行
```

### myLearnProject
```bash
cd myLearnProject
mvn clean package -DskipTests   # 打包（跳过测试）
mvn spring-boot:run             # 运行
```

### springai-project
```bash
cd springai-project
mvn clean package -DskipTests    # 打包
mvn spring-boot:run             # 运行
```

### graph2D-tool
```bash
cd graph2D-tool
mvn clean install                # 编译并安装到本地仓库
```

## 架构说明

### springai-project
- 使用 Spring AI Alibaba (DashScope) 阿里云通义千问 API
- 核心结构：
  - `controller/ChatController.java` - 对话入口
  - `tool/` - 各类 AI 工具（WeatherTool、TimeTool、PositionTool、PageTool、ExcelTool）
  - `config/AiToolConfig.java` - AI 工具配置
  - `utils/SpringContextUtil.java` - Spring 上下文工具类

### graph2D-tool
- 纯 Java 几何图形库，无外部依赖
- 核心类：`Point`、`Line`、`LineSeg`、`LineRay`、`Circle`、`Shape`
- `entity/point/Point.java` - 二维点
- `entity/shape/` - 各类图形实体

### myLearnProject
- 包含多个中间件学习示例：Kafka 消费者/生产者、RocketMQ 消息收发
- Netty 示例代码
- POI Excel 操作示例

## 编码规范

- Java 命名：类名大驼峰，方法/变量小驼峰
- 包名：`com.ningboz.{项目名}` 前缀
- Spring Boot 2.x 项目使用 Java 8 或 17，3.x 使用 Java 17

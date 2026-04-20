# Claude Code 使用教程

## 简介

Claude Code 是 Anthropic 官方推出的命令行工具，通过自然语言帮助开发者完成软件工程任务。它可以读写文件、搜索代码、执行 Bash 命令、操作 Git 等。

## 基础命令

### 斜杠命令

在对话中输入 `/` 开头的命令触发特定功能：

| 命令 | 功能 |
|------|------|
| `/help` | 显示帮助信息 |
| `/clear` | 清除当前对话上下文 |
| `/plan` | 对代码修改进行规划 |
| `/review` | 审查 Pull Request |
| `/security-review` | 安全审查 |
| `/simplify` | 简化/优化代码 |
| `/init` | 初始化 CLAUDE.md 文件 |
| `/update-config` | 配置 Claude Code 设置 |
| `/keybindings-help` | 查看/自定义键盘快捷键 |

### 退出对话

- 输入 `exit` 或 `quit` 退出 Claude Code

## 核心能力

### 文件操作

Claude Code 可以帮你完成各种文件操作：

```
# 读取文件
"帮我看一下 src/main/java/UserService.java 的内容"

# 创建文件
"帮我创建一个测试类 UserServiceTest.java"

# 编辑文件
"把 getUser 方法改成支持缓存"

# 批量修改
"把所有 Service 类的日志级别从 info 改成 debug"
```

### Git 操作

```
# 查看状态
"git 状态怎么样？"

# 创建提交
"/commit 添加用户登录功能"

# 创建分支
"帮我创建一个 feature/user-auth 分支"
```

### 代码搜索

```
# 按内容搜索
"搜索所有使用 JWT 的地方"

# 按文件名搜索
"找到所有 Test 结尾的文件"
```

### Bash 命令执行

```
# 直接执行命令
"帮我运行 mvn clean install"

# 查看文件差异
"git diff src/main/java/"
```

## 实用场景

### 场景 1：理解现有代码

```
"这个项目的结构是什么样的？"
"UserService 类负责什么功能？"
"帮我解释一下这个正则表达式"
```

### 场景 2：编写新功能

```
"帮我写一个发送邮件的工具类"
"创建一个 REST API 端点 GET /users/{id}"
"添加一个缓存层"
```

### 场景 3：调试问题

```
"帮我看看为什么这个接口响应慢"
"这个 NPE 是从哪里来的？"
"解释一下这个异常的堆栈信息"
```

### 场景 4：重构代码

```
"把这段重复代码提取成一个方法"
"帮我简化这个复杂的条件判断"
"把这个类拆分成更小的类"
```

### 场景 5：代码审查

```
"帮我 review 这个 PR"
/review
"检查一下这段代码有没有安全问题"
```

## 配置优化

### 使用 CLAUDE.md 记录项目上下文

在项目根目录创建 `.claude/CLAUDE.md`：

```markdown
# 项目名称
项目描述

## 技术栈
- Java 17
- Spring Boot 3.0
- MySQL 8.0

## 目录结构
- src/main/java - 主代码
- src/test/java - 测试代码

## 编码规范
- 遵循 Google Java Style Guide
- 所有公共方法必须有 Javadoc

## 常用命令
- mvn clean install - 构建
- mvn test - 运行测试
```

### 权限配置

减少频繁的权限提示，在 `.claude/settings.json` 中配置：

```json
{
  "permissions": {
    "allow": [
      "Bash(mvn *)",
      "Bash(npm *)",
      "Read(/**)"
    ]
  }
}
```

使用 `/less-permission-prompts` 命令可以自动扫描并添加常用只读操作的权限白名单。

## 最佳实践

1. **明确描述任务**：越具体的描述得到越准确的结果
   - ❌ "帮我看看代码"
   - ✅ "帮我检查 UserService.java 中 getUserById 方法的异常处理"

2. **分步骤进行**：复杂任务分多步完成
   - 第一步：理解需求
   - 第二步：制定计划
   - 第三步：执行修改

3. **查看修改计划**：使用 `/plan` 确认改动方向
   - "帮我写一个功能" → 先看计划 → 确认后执行

4. **利用项目上下文**：提供足够的背景信息
   - 项目类型、技术栈、编码规范等

5. **善用搜索**：先搜索再修改
   - "这个方法在哪些地方被调用？"
   - "有哪些测试覆盖了这个功能？"

## 常用快捷键

| 操作 | 说明 |
|------|------|
| `Enter` | 发送消息 |
| `Shift+Enter` | 换行输入 |
| `Ctrl+C` | 终止当前操作 |

## 获取帮助

- `/help` - 显示所有可用命令
- `/keybindings-help` - 查看快捷键配置
- 官方文档：`https://code.claude.com/docs`

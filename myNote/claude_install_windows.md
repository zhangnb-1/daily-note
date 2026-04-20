# 获取claude.exe, 不需要翻墙
winget install Anthropic.ClaudeCode

# 安装成功主要参考第一个网页
# https://help.aliyun.com/zh/model-studio/claude-code#1e6a926d3c231
# https://api-docs.deepseek.com/zh-cn/guides/anthropic_api
# https://bailian.console.aliyun.com/cn-beijing/?tab=api#/api/?type=model&url=2980295
#Set-ExecutionPolicy -Scope Process -ExecutionPolicy Bypass

# api-key网页
# https://bailian.console.aliyun.com/cn-beijing/?tab=model#/api-key

# powershell
$env:ANTHROPIC_BASE_URL="https://api.deepseek.com/anthropic"
$env:ANTHROPIC_BASE_URL="https://dashscope.aliyuncs.com/apps/anthropic"
$env:ANTHROPIC_API_KEY="YOUR_API_KEY"
$env:ANTHROPIC_MODEL="qwen-plus"
$env:HTTP_PROXY="http://127.0.0.1:7897"
$env:HTTPS_PROXY="http://127.0.0.1:7897"

# 如果事后影响了其他软件，比如猎豹加速器打不开，则改默认值空
$env:HTTP_PROXY=""
$env:HTTPS_PROXY=""


# cmd
setx ANTHROPIC_BASE_URL "https://api.deepseek.com/anthropic"
setx ANTHROPIC_BASE_URL "https://dashscope.aliyuncs.com/apps/anthropic"
setx ANTHROPIC_API_KEY "YOUR_API_KEY"
setx ANTHROPIC_MODEL "qwen-plus"
setx HTTP_PROXY "http://127.0.0.1:7897"
setx HTTPS_PROXY "http://127.0.0.1:7897"
需要新开一个cmd，否则环境变量不生效
查看环境变量命令
echo %HTTP_PROXY%
echo %HTTPS_PROXY%
# 如果事后影响了其他软件，比如猎豹加速器打不开，则改默认值空
setx HTTP_PROXY ""
setx HTTPS_PROXY ""


# 首次登录时，绕开claude登录
macOS / Linux: ~/.claude.json
Windows: C:\Users\%USERNAME%\.claude.json
新增或修改字段 "hasCompletedOnboarding": true
保存文件，然后在新终端中重新运行 claude

# 需要注意的是！后续打开claude不再需要翻墙了！

# 其他设置，比如语言，初始模型
在 C:\Users\%USERNAME%\.claude\settings.json
  "model": "qwen-plus",
  "language": "schinese"

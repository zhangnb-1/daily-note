# 第二阶段：AI和Agent基础

> 本文档是对 Java转Agent开发学习路径 中第二阶段的扩展，详细讲解AI和Agent核心概念。

---

## 2.1 大模型基础概念（4-6小时）

### 2.1.1 LLM（大语言模型）

**什么是LLM？**

LLM (Large Language Model) 是基于深度学习的大规模语言模型，能够理解和生成人类语言。

```
Java类比：可以把LLM想象成一个经验丰富的"全栈工程师"
- 训练阶段：类似Java工程师通过大量项目积累经验
- 推理阶段：类似工程师根据新问题调用已有经验给出答案
```

**主流LLM模型：**

| 模型 | 特点 | 适用场景 |
|------|------|----------|
| GPT-4 | 通用能力强 | 复杂推理、多轮对话 |
| Claude | 长文本处理强 | 文档分析、代码审查 |
| Gemini | 多模态 | 图文混合任务 |
| 通义千问 | 中文优化 | 中文应用 |
| 文心一言 | 百度中文 | 中文应用 |
| DeepSeek | 性价比高 | 成本敏感场景 |

### 2.1.2 Token（词元）

Token是文本处理的最小单位。

```python
# 重要公式：1个中文字 ≈ 2个tokens
# 这是为什么API按token计费的原因

# 示例
text = "你好，世界！"
# "你" -> token 1
# "好" -> token 2
# "，" -> token 3
# "世" -> token 4
# "界" -> token 5
# "！" -> token 6
# 总计：6个tokens

# Python中可以用tiktoken库计算
import tiktoken
encoding = tiktoken.get_encoding("cl100k_base")
tokens = encoding.encode("你好，世界！")
print(len(tokens))  # 6
```

**Java对比理解：**
```java
// Java的Tokenization类似将文本分成词元
String text = "你好，世界！";
String[] tokens = text.split(""); // ["你","好","，","世","界","！"]
// 区别：Java split是按字符，LLM的tokenization更智能
```

### 2.1.3 Embedding（向量嵌入）

Embedding将文本转换为稠密向量，实现语义表示。

```python
from openai import OpenAI

client = OpenAI()

# 将文本转为向量
response = client.embeddings.create(
    model="text-embedding-3-small",
    input="什么是人工智能？"
)
vector = response.data[0].embedding
print(f"向量维度: {len(vector)}")  # 1536维
print(f"向量前5个值: {vector[:5]}")

# 向量相似度计算
import numpy as np

def cosine_similarity(a, b):
    return np.dot(a, b) / (np.linalg.norm(a) * np.linalg.norm(b))

text1 = "苹果是水果"
text2 = "香蕉是水果"
text3 = "苹果公司生产手机"

# 获取向量
vec1 = client.embeddings.create(input=text1, model="text-embedding-3-small").data[0].embedding
vec2 = client.embeddings.create(input=text2, model="text-embedding-3-small").data[0].embedding
vec3 = client.embeddings.create(input=text3, model="text-embedding-3-small").data[0].embedding

print(f"水果之间相似度: {cosine_similarity(vec1, vec2):.4f}")  # 高
print(f"苹果vs香蕉: {cosine_similarity(vec1, vec3):.4f}")      # 低
```

**Java类比：**
```
可以理解为Java的HashMap<String, List<Double>>
key是文本，value是向量，用于语义相似度匹配
```

### 2.1.4 向量数据库

| 数据库 | 特点 | 适用场景 |
|--------|------|----------|
| Milvus | 开源、可私有部署 | 企业级应用 |
| Pinecone | 云服务、易用 | 快速启动 |
| Chroma | 轻量级、本地 | 原型开发 |
| Weaviate | 混合搜索 | 全文+向量 |

```python
# 使用Chroma（轻量级向量数据库）
import chromadb

client = chromadb.Client()
collection = client.create_collection("my_collection")

# 添加文档
collection.add(
    documents=["Python是一种编程语言", "Java也是一种编程语言"],
    ids=["doc1", "doc2"]
)

# 查询相似文档
results = collection.query(
    query_texts=["编程语言有哪些？"],
    n_results=2
)
print(results)
```

---

## 2.2 AI Agent核心原理（6-8小时）

### 2.2.1 Agent是什么？

Agent（智能体）是能够**自主决策、调用工具、保持记忆**的AI系统。

```
Java架构类比：
Agent ≈ Spring MVC + Service + Repository的综合体
- 感知环境（类似Controller接收请求）
- 决策思考（类似Service处理业务逻辑）
- 调用工具（类似调用外部API）
- 记忆存储（类似Repository持久化数据）
```

### 2.2.2 ReAct循环

ReAct = Reasoning + Acting（推理+行动）

```
┌─────────────────────────────────────┐
│         Agent核心循环               │
├─────────────────────────────────────┤
│  1. Thought (思考)                 │
│     分析当前状态，决定下一步行动     │
│  2. Action (行动)                  │
│     调用工具或API                   │
│  3. Observation (观察)              │
│     获取行动结果                    │
│  4. 循环直到完成任务                │
└─────────────────────────────────────┘
```

```python
# ReAct模式的简化实现
class SimpleAgent:
    def __init__(self):
        self.history = []

    def run(self, task):
        thought = f"分析任务: {task}"
        print(thought)
        self.history.append(thought)

        # 模拟Action
        action = "调用搜索API查找相关信息"
        print(f"行动: {action}")
        self.history.append(action)

        # 模拟Observation
        observation = "找到3条相关信息"
        print(f"观察: {observation}")
        self.history.append(observation)

        # 再次思考
        thought2 = "信息足够，生成回答"
        print(thought2)

        return "基于搜索结果的回答"

agent = SimpleAgent()
result = agent.run("什么是LangChain")
```

### 2.2.3 Planning（规划）

Agent需要将复杂任务分解为简单步骤。

```python
# 任务分解示例
task = "帮我分析竞争对手并制定营销策略"

# 分解步骤
steps = [
    "1. 搜索竞争对手基本信息",
    "2. 收集产品特点和定价",
    "3. 分析市场份额和用户评价",
    "4. 基于分析结果制定策略"
]

for step in steps:
    print(f"执行: {step}")
    # 实际中由Agent自动执行每一步
```

### 2.2.4 Memory（记忆）

Agent的记忆分为三种类型：

| 记忆类型 | 说明 | 类比Java |
|----------|------|----------|
| Short-term | 当前对话上下文 | HttpSession |
| Long-term | 跨会话持久化 | Database |
| Working | 临时计算状态 | 变量堆 |

```python
# LangChain的Memory组件
from langchain.memory import ConversationBufferMemory

memory = ConversationBufferMemory()

# 存储对话
memory.save_context({"input": "你好"}, {"output": "你好！有什么可以帮你？"})
memory.save_context({"input": "LangChain是什么"}, {"output": "LangChain是一个用于构建LLM应用的框架。"})

# 获取历史
chat_history = memory.load_memory_variables({})
print(chat_history)
```

### 2.2.5 Tool Use（工具调用）

Tools让Agent能够与外部世界交互。

```python
from langchain.agents import AgentType, initialize_agent
from langchain_openai import ChatOpenAI
from langchain.tools import Tool

# 定义工具
def search_weather(city):
    """查询城市天气"""
    return f"{city}今天晴天，25度"

def search_news(topic):
    """搜索新闻"""
    return f"关于{topic}的最新新闻：..."

tools = [
    Tool(name="weather", func=search_weather, description="查询天气"),
    Tool(name="news", func=search_news, description="搜索新闻")
]

# 初始化Agent
llm = ChatOpenAI(temperature=0)
agent = initialize_agent(
    tools, llm, agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION
)

# Agent自动选择工具执行
result = agent.run("北京今天天气怎么样？")
print(result)
```

---

## 2.3 主流框架概览（4-6小时）

### 2.3.1 LangChain

最流行的LLM应用开发框架。

```python
# LangChain核心组件
from langchain_core.messages import HumanMessage
from langchain_openai import ChatOpenAI

# Model I/O - 模型调用
llm = ChatOpenAI(model="gpt-4")
response = llm.invoke([HumanMessage(content="你好")])

# Chains - 链式调用
from langchain_core.prompts import ChatPromptTemplate

prompt = ChatPromptTemplate.from_messages([
    ("system", "你是一个专业的{role}"),
    ("human", "{question}")
])

chain = prompt | llm
result = chain.invoke({"role": "律师", "question": "合同纠纷怎么处理？"})

# Agents - 自主决策
# 详见第三阶段文档
```

### 2.3.2 AutoGen

微软出品的Multi-Agent框架。

```python
# AutoGen示例
import autogen

# 定义助手Agent
assistant = autogen.AssistantAgent(
    name="assistant",
    llm_config={"model": "gpt-4"}
)

# 定义用户代理
user_proxy = autogen.UserProxyAgent(
    name="user_proxy",
    human_input_mode="NEVER"
)

# 启动对话
user_proxy.initiate_chat(
    assistant,
    message="帮我写一个Python快速排序"
)
```

### 2.3.3 CrewAI

专注于多Agent协作的框架。

```python
# CrewAI示例
from crewai import Agent, Task, Crew

# 定义Agent
researcher = Agent(
    role="研究员",
    goal="获取最准确的信息",
    backstory="你是资深的AI研究员"
)

writer = Agent(
    role="作家",
    goal="撰写引人入胜的文章",
    backstory="你是专业的内容作家"
)

# 定义任务
task1 = Task(description="研究AI最新进展", agent=researcher)
task2 = Task(description="写一篇相关文章", agent=writer)

# 创建Crew
crew = Crew(agents=[researcher, writer], tasks=[task1, task2])
result = crew.kickoff()
```

### 2.3.4 LlamaIndex

专注于知识检索增强（RAG）的框架。

```python
# LlamaIndex示例
from llama_index import VectorStoreIndex, SimpleDirectoryReader

# 加载文档
documents = SimpleDirectoryReader("./data").load_data()

# 构建索引
index = VectorStoreIndex.from_documents(documents)

# 查询
query_engine = index.as_query_engine()
response = query_engine.query("文章主要讲了什么？")
```

### 2.3.5 框架对比

| 框架 | 特点 | 适用场景 | Java类比 |
|------|------|----------|----------|
| LangChain | 全能型、组件丰富 | 各类LLM应用 | Spring Boot |
| AutoGen | Multi-Agent、对话 | 多Agent协作 | Spring Cloud |
| CrewAI | 任务导向、易用 | 多Agent任务 | 工作流引擎 |
| LlamaIndex | RAG优化 | 知识问答 | Elasticsearch |

---

## 2.4 核心概念速查表

| 概念 | 英文 | 解释 | Java类比 |
|------|------|------|----------|
| 大语言模型 | LLM | 能理解和生成文本的AI模型 | 经验丰富的工程师 |
| 词元 | Token | 文本处理的最小单位 | 字符串分词 |
| 向量嵌入 | Embedding | 将文本转为向量 | Feature Vector |
| 向量数据库 | Vector DB | 存储和检索向量 | NoSQL数据库 |
| 智能体 | Agent | 自主决策的系统 | 微服务 |
| 推理行动 | ReAct | 思考-行动-观察循环 | 状态机 |
| 规划 | Planning | 任务分解 | 工作流 |
| 记忆 | Memory | 存储对话/知识 | 会话/数据库 |
| 工具调用 | Tool Use | Agent调用外部系统 | RPC调用 |
| 检索增强 | RAG | 结合知识库回答问题 | 查询+推理 |

---

## 2.5 练习题

1. **Token计算**：使用tiktoken计算"Hello, World!"和"你好，世界！"的token数量，对比差异
2. **向量相似度**：用OpenAI Embedding计算"苹果"和"香蕉"、"苹果和"iPhone"的相似度差异
3. **ReAct实现**：实现一个简单的ReAct Agent，能够通过搜索工具回答问题
4. **框架对比**：分别用LangChain和LlamaIndex实现同一个RAG场景，对比代码量

---

## 2.6 常见问题

| 问题 | 答案 |
|------|------|
| Token和字符有什么区别？ | 1中文约等于2个tokens，1英文单词约等于1-2个tokens |
| 为什么需要向量数据库？ | 用于实现语义相似度搜索，普通的LIKE查询无法做到 |
| Agent和普通API有什么区别？ | Agent能自主决策、记忆状态、多轮对话，普通API是无状态的 |
| ReAct的"Thought"是什么？ | 让LLM解释为什么要这么做，增加推理透明度 |

---

*本文档生成时间：2026年4月18日*

# 第三阶段：LangChain深度学习

> 本文档是对 Java转Agent开发学习路径 中第三阶段的扩展，深入讲解LangChain框架。

---

## 3.1 LangChain核心组件（8-10小时）

LangChain由六大核心组件构成：

```
┌────────────────────────────────────────────────────────────┐
│                    LangChain架构                          │
├────────────────────────────────────────────────────────────┤
│  Model I/O    │ 输入输出管理，Prompt模板，输出解析        │
│  Retrieval    │ 外部知识检索，向量数据库集成                │
│  Chains      │ 链式调用，组合多个组件                      │
│  Agents      │ 自主决策，工具调用，任务执行                │
│  Memory      │ 对话历史，跨会话记忆                        │
│  Callbacks   │ 事件回调，日志记录，监控                    │
└────────────────────────────────────────────────────────────┘
```

### 3.1.1 Model I/O（模型输入输出）

**Java类比**：`Model I/O` 类似 Spring的 `RestTemplate` 或 `WebClient`，是调用外部服务的抽象。

```python
from langchain_openai import ChatOpenAI
from langchain_core.messages import HumanMessage, SystemMessage

# 1. 基础调用
llm = ChatOpenAI(model="gpt-4", temperature=0.7)

response = llm.invoke([
    HumanMessage(content="用一句话解释量子计算")
])
print(response.content)

# 2. 系统提示词
llm_with_system = ChatOpenAI(model="gpt-4")
response = llm_with_system.invoke([
    SystemMessage(content="你是一个古诗生成器，每次只输出一首诗"),
    HumanMessage(content="写一首关于春天的诗")
])
print(response.content)

# 3. 流式输出（类似Server-Sent Events）
for chunk in llm.stream("讲一个笑话"):
    print(chunk.content, end="", flush=True)
```

### 3.1.2 Prompt模板

```python
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser

# 1. 基本模板
prompt = ChatPromptTemplate.from_messages([
    ("system", "你是一个{language}翻译助手"),
    ("human", "把下面的句子翻译成英文：{sentence}")
])

# 2. 链式组合
chain = prompt | llm | StrOutputParser()
result = chain.invoke({
    "language": "专业",
    "sentence": "人工智能正在改变世界"
})
print(result)

# 3. Few-shot提示
prompt_with_examples = ChatPromptTemplate.from_messages([
    ("system", "你是一个情感分析助手"),
    ("human", "评论：这家餐厅的服务很好。\n情感：正面"),
    ("human", "评论：等了半小时才上菜。\n情感：负面"),
    ("human", "评论：{review}\n情感：")
])

chain = prompt_with_examples | llm
result = chain.invoke({"review": "味道一般，环境嘈杂"})
print(result)  # 输出: 负面
```

### 3.1.3 Output Parser（输出解析）

```python
from langchain_core.output_parsers import CommaSeparatedListOutputParser
from langchain_openai import ChatOpenAI

# 解析为列表
parser = CommaSeparatedListOutputParser()
prompt = ChatPromptTemplate.from_messages([
    ("system", "列出{topic}的5个特点，用逗号分隔"),
    ("human", "{topic}")
])

chain = prompt | llm | parser
result = chain.invoke({"topic": "Java语言的优点"})
print(result)  # ['面向对象', '跨平台', ...]

# 自定义解析
from pydantic import BaseModel, Field

class MovieReview(BaseModel):
    sentiment: str = Field(description="情感：正面或负面")
    score: int = Field(description="评分1-10")
    key_points: list = Field(description="关键点列表")

from langchain.output_parsers import PydanticOutputParser
output_parser = PydanticOutputParser(pydantic_object=MovieReview)

prompt = ChatPromptTemplate.from_messages([
    ("system", "{format_instructions}"),
    ("human", "评价电影《流浪地球》")
])

chain = prompt | llm | output_parser
result = chain.invoke({"format_instructions": output_parser.get_format_instructions()})
print(result.sentiment)
print(result.score)
```

---

## 3.2 Tool Calling开发（6-8小时）

### 3.2.1 工具定义

```python
from langchain.tools import Tool
from langchain_openai import ChatOpenAI
from langchain.agents import AgentType, initialize_agent

# 1. 基于函数的工具
def get_weather(city: str) -> str:
    """查询城市天气

    Args:
        city: 城市名称

    Returns:
        天气信息
    """
    weathers = {
        "北京": "晴天，25°C",
        "上海": "多云，28°C",
        "广州": "雨天，30°C"
    }
    return weathers.get(city, "未知城市")

def calculate(expression: str) -> str:
    """计算数学表达式

    Args:
        expression: 数学表达式，如 "2+3*5"

    Returns:
        计算结果
    """
    try:
        result = eval(expression)
        return str(result)
    except:
        return "计算错误"

# 2. 创建工具列表
tools = [
    Tool(
        name="weather",
        func=get_weather,
        description="当需要查询天气时使用，输入应该是城市名称"
    ),
    Tool(
        name="calculator",
        func=calculate,
        description="当需要计算数学表达式时使用，输入应该是算术表达式"
    )
]

# 3. 初始化Agent
llm = ChatOpenAI(temperature=0)
agent = initialize_agent(
    tools,
    llm,
    agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION,
    verbose=True  # 打印思考过程
)

# 4. 运行Agent
result = agent.run("北京今天天气怎么样？顺便帮我算一下2加3乘以5等于多少")
print(result)
```

### 3.2.2 Function Calling（OpenAI格式）

```python
from openai import OpenAI
from langchain.tools import tool

client = OpenAI()

# 使用@tool装饰器定义工具
@tool
def get_stock_price(symbol: str) -> str:
    """获取股票价格

    Args:
        symbol: 股票代码，如 AAPL, GOOGL
    """
    prices = {"AAPL": 178.50, "GOOGL": 142.30, "MSFT": 378.91}
    return f"{symbol}: ${prices.get(symbol, '未知')}"

@tool
def buy_stock(symbol: str, quantity: int) -> str:
    """购买股票

    Args:
        symbol: 股票代码
        quantity: 购买数量
    """
    return f"成功购买 {quantity} 股 {symbol}"

# 获取工具定义
tools = [get_stock_price, buy_stock]
functions = [
    {
        "type": "function",
        "function": {
            "name": tool.name,
            "description": tool.description,
            "parameters": {
                "type": "object",
                "properties": tool.get_input_schema().schema(),
                "required": []
            }
        }
    }
    for tool in tools
]

# 调用API
messages = [
    {"role": "user", "content": "帮我买100股苹果股票"}
]

response = client.chat.completions.create(
    model="gpt-4-turbo",
    messages=messages,
    tools=functions,
    tool_choice="auto"
)

# 处理工具调用
if response.choices[0].message.tool_calls:
    for tool_call in response.choices[0].message.tool_calls:
        func_name = tool_call.function.name
        func_args = json.loads(tool_call.function.arguments)
        print(f"调用工具: {func_name}, 参数: {func_args}")
```

### 3.2.3 工具选择策略

```python
from langchain.agents import AgentType, initialize_agent
from langchain_openai import ChatOpenAI

llm = ChatOpenAI(temperature=0)

# ZERO_SHOT_REACT_DESCRIPTION - 根据描述自动选择
agent1 = initialize_agent(
    tools, llm, agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION
)

# CONVERSATIONAL_REACT_DESCRIPTION - 适合对话场景
agent2 = initialize_agent(
    tools, llm, agent=AgentType.CONVERSATIONAL_REACT_DESCRIPTION
)

# CHAT_ZERO_SHOT_REACT_DESCRIPTION - Chat模型优化
agent3 = initialize_agent(
    tools, llm, agent=AgentType.CHAT_ZERO_SHOT_REACT_DESCRIPTION
)
```

---

## 3.3 Chain表达式 LCEL（5-8小时）

LCEL (LangChain Expression Language) 是LangChain的链式调用语法。

### 3.3.1 管道操作符 |

```python
from langchain_openai import ChatOpenAI
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser

llm = ChatOpenAI(model="gpt-4")

# 基本链
prompt = ChatPromptTemplate.from_messages([
    ("system", "你是一个{subject}专家"),
    ("human", "{question}")
])

chain = prompt | llm | StrOutputParser()

result = chain.invoke({
    "subject": "Python",
    "question": "什么是装饰器？"
})
```

### 3.3.2 Runnable接口

```python
from langchain_core.runnables import RunnablePassthrough

# RunnablePassthrough - 透传输入
chain = RunnablePassthrough()

# 链组合
prompt = ChatPromptTemplate.from_messages([
    ("system", "总结以下文本："),
    ("human", "{text}")
])

# 方式1：管道
summary_chain = prompt | llm | StrOutputParser()

# 方式2：compose
from langchain_core.runnables import RunnableParallel

# 并行执行
parallel_chain = RunnableParallel(
    summary=summary_chain,
    word_count=lambda x: len(x["text"].split())
)

result = parallel_chain.invoke({"text": "这是一个测试文本"})
# result = {"summary": "总结内容", "word_count": 7}
```

### 3.3.3 常用链类型

```python
# 1. Simple Chain
from langchain import LLMChain
from langchain.prompts import PromptTemplate

prompt = PromptTemplate.from_template("用{language}实现{function}")
chain = LLMChain(llm=llm, prompt=prompt)
result = chain.run(language="Python", function="快速排序")

# 2. Sequential Chain（顺序链）
from langchain.chains import SimpleSequentialChain

template1 = PromptTemplate.from_template("把{product}翻译成英文")
chain1 = LLMChain(llm=llm, prompt=template1)

template2 = PromptTemplate.from_template("用英文描述：{english_product}")
chain2 = LLMChain(llm=llm, prompt=template2)

sequential_chain = SimpleSequentialChain(chains=[chain1, chain2])
result = sequential_chain.run(product="人工智能")

# 3. Router Chain（路由链）
from langchain.chains import LLMRouterChain
from langchain.prompts import PromptTemplate

router_prompt = PromptTemplate.from_template("""
根据用户输入选择对应的链：
- 如果是技术问题 -> tech_chain
- 如果是生活问题 -> life_chain

用户输入: {input}
""")
router_chain = LLMRouterChain(llm=llm, prompt=router_prompt)
```

---

## 3.4 RAG实战（6-9小时）

### 3.4.1 文档加载与分割

```python
# 1. 加载文档
from langchain_community.document_loaders import PyPDFLoader, TextLoader, WebLoader

# PDF
loader = PyPDFLoader("document.pdf")
pages = loader.load()

# 文本文件
loader = TextLoader("article.txt", encoding="utf-8")
docs = loader.load()

# 网页
loader = WebLoader("https://example.com/article")
docs = loader.load()

# 2. 文本分割
from langchain.text_splitter import RecursiveCharacterTextSplitter

splitter = RecursiveCharacterTextSplitter(
    chunk_size=500,      # 每块大小（字符数）
    chunk_overlap=50,     # 块之间重叠
    length_function=len,  # 计算长度函数
    separators=["\n\n", "\n", "。", "，"]  # 分隔符优先级
)

chunks = splitter.split_documents(docs)
print(f"分割成 {len(chunks)} 个块")

# 3. 转换
from langchain.docstore.document import Document

for i, chunk in enumerate(chunks):
    print(f"块{i+1}: {chunk.page_content[:50]}...")
```

### 3.4.2 向量嵌入与存储

```python
# 1. 使用OpenAI Embedding
from langchain_openai import OpenAIEmbeddings
from langchain_community.vectorstores import Chroma

embeddings = OpenAIEmbeddings(model="text-embedding-3-small")

# 2. 存储到向量数据库
vectorstore = Chroma.from_documents(
    documents=chunks,
    embedding=embeddings,
    persist_directory="./chroma_db"  # 持久化存储
)

# 3. 相似度检索
query = "文档主要讲了什么内容？"
results = vectorstore.similarity_search(query, k=3)  # 返回Top-K

for i, doc in enumerate(results):
    print(f"结果{i+1}: {doc.page_content[:100]}...")
```

### 3.4.3 完整RAG流程

```python
from langchain_openai import ChatOpenAI, OpenAIEmbeddings
from langchain_community.vectorstores import Chroma
from langchain_core.prompts import ChatPromptTemplate
from langchain_core.output_parsers import StrOutputParser

# 1. 初始化组件
llm = ChatOpenAI(model="gpt-4", temperature=0)
embeddings = OpenAIEmbeddings()

# 2. 加载知识库
vectorstore = Chroma(persist_directory="./chroma_db", embedding_function=embeddings)

# 3. 构建检索链
retriever = vectorstore.as_retriever(search_kwargs={"k": 3})

# 4. 构建RAG Prompt
rag_prompt = ChatPromptTemplate.from_messages([
    ("system", "基于以下参考材料回答问题。如果无法从中找到答案，请如实说明。\n\n参考材料：\n{context}"),
    ("human", "{question}")
])

# 5. 组装链
def format_docs(docs):
    return "\n\n".join([f"材料{i+1}: {doc.page_content}" for i, doc in enumerate(docs)])

rag_chain = (
    {"context": retriever | format_docs, "question": RunnablePassthrough()}
    | rag_prompt
    | llm
    | StrOutputParser()
)

# 6. 查询
result = rag_chain.invoke("这篇文档的核心观点是什么？")
print(result)
```

### 3.4.4 高级RAG技术

```python
# 1. MultiQueryRetriever - 多角度检索
from langchain.retrievers.multi_query import MultiQueryRetriever

retriever = MultiQueryRetriever.from_llm(
    retriever=vectorstore.as_retriever(),
    llm=llm
)

# 2. ContextualCompression - 上下文压缩
from langchain.retrievers import ContextualCompressionRetriever
from langchain_community.compressors import LLMChainCompressor

compressor = LLMChainCompressor(llm=llm)
compression_retriever = ContextualCompressionRetriever(
    base_retriever=retriever,
    compressors=[compressor]
)

# 3. Ensemble Retriever - 组合检索
from langchain.retrievers.ensemble import EnsembleRetriever

bm25_retriever = vectorstore.as_retriever(search_type="mmr")
vector_retriever = vectorstore.as_retriever(search_kwargs={"k": 10})

ensemble_retriever = EnsembleRetriever(
    retrievers=[bm25_retriever, vector_retriever],
    weights=[0.3, 0.7]
)
```

---

## 3.5 Spring与LangChain对照表

| Spring概念 | LangChain对应 | 说明 |
|------------|--------------|------|
| RestTemplate | Model I/O | HTTP调用外部服务 |
| @Bean | Tool装饰器 | 定义可复用的组件 |
| @AOP | Callback | 切面编程 |
| Chain of Responsibility | Chain | 责任链模式 |
| @SessionScope | Memory | 会话级存储 |
| @Repository | Retriever | 数据访问 |
| Service层 | LCEL Chain | 业务逻辑组合 |
| Strategy模式 | Agent | 策略切换 |

---

## 3.6 练习题

1. **Prompt工程**：使用Prompt模板实现一个代码审查助手，能指出代码问题
2. **工具开发**：创建一个GitHub工具，支持搜索仓库、获取Issue
3. **RAG实现**：基于本地文档构建一个问答系统，支持PDF和TXT
4. **Chain组合**：实现一个翻译→总结→评论的三步工作流

---

*本文档生成时间：2026年4月18日*

# 第一阶段：Python基础

> 本文档是对 Java转Agent开发学习路径 中第一阶段的扩展，详细讲解Python基础知识点。

---

## 1.1 Python语法基础（5-8小时）

### 1.1.1 缩进与语法特点

Python使用缩进来表示代码块，不像Java使用大括号。

```python
# Java
if (condition) {
    doSomething();
}

// Python
if condition:
    do_something()
```

### 1.1.2 动态类型

Python是动态类型语言，变量不需要声明类型。

```python
# Java
String name = "Java";
int count = 10;

// Python
name = "Python"  # str类型
count = 10       # int类型
# 变量类型可以随时改变
name = 123       # 现在是int类型
```

### 1.1.3 列表操作

```python
# 创建列表
fruits = ["apple", "banana", "orange"]

# 切片操作（类似Java的subList但更强大）
fruits[0:2]      # ["apple", "banana"]
fruits[-1]        # "orange"（最后一个元素）
fruits[::2]       # ["apple", "orange"]（步长为2）

# 列表推导式（Java 8 Stream类似）
squares = [x**2 for x in range(10)]
squares_even = [x**2 for x in range(10) if x % 2 == 0]
```

### 1.1.4 字典操作

```python
# 创建字典
person = {"name": "张三", "age": 25}

# 字典推导式
squares_dict = {x: x**2 for x in range(5)}

#items()遍历
for key, value in person.items():
    print(f"{key}: {value}")
```

---

## 1.2 面向对象编程（5-8小时）

### 1.2.1 类与对象

```python
# Java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void greet() {
        System.out.println("Hello, " + name);
    }
}

# Python
class Person:
    def __init__(self, name: str, age: int):  # __init__类似构造函数
        self.name = name      # self类似this
        self.age = age

    def greet(self):
        print(f"Hello, {self.name}")

# 使用
p = Person("张三", 25)
p.greet()
```

### 1.2.2 继承与多态

```python
class Animal:
    def speak(self):
        raise NotImplementedError

class Dog(Animal):  # 类似Java的extends
    def speak(self):
        return "Woof!"

class Cat(Animal):
    def speak(self):
        return "Meow!"

# 多态
def make_speak(animal: Animal):
    print(animal.speak())

make_speak(Dog())   # Woof!
make_speak(Cat())   # Meow!
```

### 1.2.3 装饰器（@Decorator）

装饰器是Python的核心概念，类似Java的AOP切面编程。

```python
# 简单装饰器
def log_calls(func):
    def wrapper(*args, **kwargs):
        print(f"Calling {func.__name__}")
        result = func(*args, **kwargs)
        print(f"{func.__name__} finished")
        return result
    return wrapper

@log_calls
def add(a, b):
    return a + b

# 使用装饰器
add(1, 2)
# 输出:
# Calling add
# add finished

# 带参数的装饰器
def repeat(times):
    def decorator(func):
        def wrapper(*args, **kwargs):
            for _ in range(times):
                func(*args, **kwargs)
        return wrapper
    return decorator

@repeat(3)
def say_hello():
    print("Hello!")

say_hello()  # Hello! Hello! Hello!
```

---

## 1.3 异步编程（4-6小时）

### 1.3.1 async/await 基础

Python的异步编程与Java的CompletableFuture类似。

```python
import asyncio

# Java: CompletableFuture.supplyAsync()
# Python: async def + await

async def fetch_data():
    await asyncio.sleep(1)  # 模拟IO操作
    return "Data loaded"

async def main():
    result = await fetch_data()
    print(result)

# 运行异步函数
asyncio.run(main())
```

### 1.3.2 并发执行

```python
async def task1():
    await asyncio.sleep(1)
    return "Task 1"

async def task2():
    await asyncio.sleep(2)
    return "Task 2"

async def main():
    # 并发执行两个任务（类似Java的CompletableFuture.allOf）
    results = await asyncio.gather(task1(), task2())
    print(results)  # ["Task 1", "Task 2"]

asyncio.run(main())
```

### 1.3.3 Event Loop

```python
# 创建事件循环
loop = asyncio.new_event_loop()
asyncio.set_event_loop(loop)

try:
    loop.run_until_complete(main())
finally:
    loop.close()
```

---

## 1.4 开发环境（2-4小时）

### 1.4.1 pip 与虚拟环境

```bash
# Python + pip类似 Maven
pip install langchain openai

# 创建虚拟环境（类似Java的Maven wrapper）
python -m venv myenv

# 激活虚拟环境
# Windows:
myenv\Scripts\activate
# Linux/Mac:
source myenv/bin/activate

# 导出依赖（类似pom.xml）
pip freeze > requirements.txt

# 安装依赖
pip install -r requirements.txt
```

### 1.4.2 Conda（可选）

```bash
# Conda类似Maven + Docker的结合
conda create -n myenv python=3.11
conda activate myenv
conda install pip
```

### 1.4.3 项目结构对比

```
# Java Maven项目
src/main/java/com/example/
├── controller/
├── service/
├── repository/
└── model/
pom.xml

# Python项目
my_project/
├── src/
│   └── my_project/
│       ├── __init__.py
│       ├── main.py
│       └── utils.py
├── tests/
├── requirements.txt
└── pyproject.toml
```

---

## 1.5 Java与Python对照表

| Java概念 | Python对应 | 说明 |
|----------|------------|------|
| `this` | `self` | 实例引用 |
| `public/private/protected` | `_` / `__` | 访问控制 |
| `interface` | `ABC` (Abstract Base Class) | 抽象类 |
| `@Autowired` | 依赖注入 | Spring注入依赖 |
| AOP切面 | `@decorator` | 装饰器模式 |
| `CompletableFuture` | `async/await` + `asyncio` | 异步编程 |
| Stream API | 列表推导式 + 生成器 | 函数式操作 |
| `List<String>` | `List[str]` (类型注解) | 类型提示 |
| `Maven` | `pip` + `venv` | 包管理 |
| `Spring Boot` | `FastAPI` / `Flask` | Web框架 |

---

## 1.6 练习题

1. **基础练习**：实现一个简单的`Calculator`类，支持加减乘除操作
2. **装饰器练习**：编写一个`@timer`装饰器，记录函数执行时间
3. **异步练习**：用async实现一个并发请求多个API的工具类
4. **推导式练习**：将一个整数列表转换为字典，key为原值，value为平方

---

## 1.7 常见错误与解决方案

| 错误 | 原因 | 解决方案 |
|------|------|----------|
| `IndentationError` | 缩进不一致 | 使用4个空格或配置IDE |
| `NameError: name 'xxx' is not defined` | 变量未定义或作用域错误 | 检查变量作用域 |
| `TypeError: unsupported operand type` | 类型不匹配 | 检查数据类型 |
| `ModuleNotFoundError` | 模块未安装 | `pip install module_name` |

---

*本文档生成时间：2026年4月18日*

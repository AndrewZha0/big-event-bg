# 表现（适配）模块
承载系统“对外提供的服务和表现的行为“的实现

包含几个部分：
1. client 中 API 的实现，目前主要是通过 Web 的 Controller 方式实现
2. 消息消费者实现
3. 定时任务回调实现

# 下游模块
* application：以上各类部分，只负责进行适配实现。实际业务逻辑，转发给对应的 application service 中
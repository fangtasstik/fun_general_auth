• 两天原型的话，优先级这样排最稳：先保证“能登录、能鉴权、能管人和角色”。

  Stage 1（必须实现）

  - security：JWT 过滤器、鉴权拦截、方法级权限校验
  - auth：登录、token 签发/刷新、登出
  - identity：用户基本 CRUD、密码加密、账号启停
  - access：角色/权限/用户-角色绑定、权限判定
  - api：登录/用户/角色/权限的 REST 接口 + 校验
  - persistence：用户/角色/权限表 + repository
  - common：统一响应、异常处理、错误码

  Stage 2（加分项）

  - audit：登录/授权/变更日志
  - integration：外部 IdP / 合作方 API 适配
  - infra：缓存权限、消息队列、限流
  - domain：更完整的领域模型抽象
  - config：更细的安全/性能配置（token 黑名单、登录风控）

<!-- ##################################################################### -->
<!-- ##################################################################### -->
<!-- ##################################################################### -->

  结构建议（单体服务内模块化）

  - config：全局配置（数据源、缓存、消息、OpenAPI、审计、线程池）
  - security：认证鉴权核心（OAuth2/JWT、过滤器链、方法级权限、RBAC）
  - identity：用户、组织、租户、账户状态、密码策略、MFA 等
  - access：角色/权限/资源定义、授权策略、权限计算
  - auth：登录、token 签发/刷新、session 管理、登录风控
  - integration：外部系统对接（SSO、第三方 IdP、合作方 API）
  - api：REST 控制层（DTO、参数校验、异常映射）
  - domain：领域模型、聚合根、领域服务（尽量无框架依赖）
  - persistence：仓储接口 + JPA/MyBatis 实现
  - common：工具类、常量、错误码、通用响应、基础枚举
  - audit：审计日志、登录/授权操作轨迹
  - infra：消息队列、缓存、文件、第三方 SDK 适配

  包/目录示例

  src/main/java/com/company/authsystem
  ├─ api
  │  ├─ controller
  │  ├─ dto
  │  └─ mapper
  ├─ auth
  │  ├─ service
  │  ├─ token
  │  └─ session
  ├─ identity
  │  ├─ model
  │  ├─ service
  │  └─ policy
  ├─ access
  │  ├─ model
  │  ├─ service
  │  └─ policy
  ├─ security
  │  ├─ config
  │  ├─ filter
  │  └─ annotation
  ├─ integration
  │  ├─ idp
  │  └─ partner
  ├─ persistence
  │  ├─ entity
  │  ├─ repository
  │  └─ mapper
  ├─ domain
  │  ├─ user
  │  ├─ role
  │  └─ permission
  ├─ audit
  ├─ config
  ├─ common
  └─ infra

  为什么这样划分（面试可用的讲法）

  - 认证、身份、权限拆开，便于独立演进（符合 JD 的 auth/identity/access-control）。
  - integration 单独隔离对接合作方系统，降低耦合。
  - security 只负责安全框架层，业务在 auth/identity/access 中落地。
  - domain 保持业务核心干净，可迁移到多模块或微服务。

  配套设计要点

  - 认证：OAuth2 + JWT，支持 refresh token；登录风控（失败次数、锁定策略）。
  - 授权：RBAC + 资源权限（角色→权限→资源），支持方法级注解和 URL 级控制。
  - 数据层：用户/角色/权限/资源/审计日志表清晰分离。
  - 对接：外部 IdP / 合作方 API 通过适配器或接口抽象，避免侵入主流程。

<!-- ##################################################################### -->
<!-- ##################################################################### -->
<!-- ##################################################################### -->

# 明日新闻

说明：这是我的毕业设计，其实已经做了好久了，而且是存储在另一个隐私仓库里边，但是因为那个仓库存有我论文什么的，为了保密，又为了分享我这个项目，所以另开一个仓库存储我这个毕业设计项目。

## 效果图

### 日间：

| ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061820580.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061820585.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061820584.jpg) |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061820583.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061820586.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061820587.jpg) |
| ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061820590.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061820591.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061820589.jpg) |
|                                                              |                                                              |                                                              |

### 夜间：


| ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061833608.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061833612.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061833611.jpg) |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061833610.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061833613.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061833614.jpg) |
| ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061833618.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061833619.jpg) | ![](https://cdn.jsdelivr.net/gh/fushaolei/img2/new/202110061833616.jpg) |
|                                                              |                                                              |                                                              |

## 主要模块：

**总体优化**

- [x] MVP架构
- [x] 可切换的日夜间模式
- [x] 未登录处理
- [x] 组件化

**新闻**

- [x] 简单的新闻feed流
- [x] 新闻详情页 图文混排
- [x] 收藏，点赞，评论，分享

**视频**

- [x] 简单的视频feed流
- [x] 视频播放
- [x] 视频全屏处理
- [x] 收藏，点赞，评论，分享

**我的**

- [x] 登录成功后缓存
- [x] 登录注册模块
- [x] 可以修改个人信息
- [x] 可上传头像
- [x] 可查看我的收藏

## 技术

Android端：MVP架构 + Retrofit + RxJava + MMKV + Glide + RecyclerView + Jzvd

服务器端：SpringBoot + MyBatis

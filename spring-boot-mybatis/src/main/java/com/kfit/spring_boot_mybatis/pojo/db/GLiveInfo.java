package com.kfit.spring_boot_mybatis.pojo.db;

import javax.persistence.*;

@Table(name = "t_rtfrontier_live_info")
public class GLiveInfo {
    /**
     * 直播id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 直播名称
     */
    private String name;

    /**
     * 封面
     */
    @Column(name = "cover_url")
    private String coverUrl;

    /**
     * 封面小图
     */
    @Column(name = "cover_small_url")
    private String coverSmallUrl;

    /**
     * 封面中图
     */
    @Column(name = "cover_middle_url")
    private String coverMiddleUrl;

    /**
     * 分享摘要
     */
    @Column(name = "abstract_info")
    private String abstractInfo;

    /**
     * 讲师id
     */
    @Column(name = "lecturer_id")
    private Integer lecturerId;

    /**
     * 内容标签
     */
    private String content;

    /**
     * 是否删除 1是 0否
     */
    @Column(name = "is_drop")
    private String isDrop;

    /**
     * 创建时间
     */
    private Integer ctime;

    /**
     * 修改时间
     */
    private Integer mtime;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Integer startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Integer endTime;

    /**
     * 显示状态 1显示 0禁用
     */
    private String state;

    /**
     * 点击量
     */
    @Column(name = "popularity_index")
    private Integer popularityIndex;

    /**
     * 发布时间
     */
    @Column(name = "published_time")
    private Integer publishedTime;

    /**
     * 用于前端判断的字段
     */
    @Column(name = "add_id")
    private Integer addId;

    /**
     * 直播介绍
     */
    private String introduce;

    /**
     * 获取直播id
     *
     * @return id - 直播id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置直播id
     *
     * @param id 直播id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取直播名称
     *
     * @return name - 直播名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置直播名称
     *
     * @param name 直播名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取封面
     *
     * @return cover_url - 封面
     */
    public String getCoverUrl() {
        return coverUrl;
    }

    /**
     * 设置封面
     *
     * @param coverUrl 封面
     */
    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl == null ? null : coverUrl.trim();
    }

    /**
     * 获取封面小图
     *
     * @return cover_small_url - 封面小图
     */
    public String getCoverSmallUrl() {
        return coverSmallUrl;
    }

    /**
     * 设置封面小图
     *
     * @param coverSmallUrl 封面小图
     */
    public void setCoverSmallUrl(String coverSmallUrl) {
        this.coverSmallUrl = coverSmallUrl == null ? null : coverSmallUrl.trim();
    }

    /**
     * 获取封面中图
     *
     * @return cover_middle_url - 封面中图
     */
    public String getCoverMiddleUrl() {
        return coverMiddleUrl;
    }

    /**
     * 设置封面中图
     *
     * @param coverMiddleUrl 封面中图
     */
    public void setCoverMiddleUrl(String coverMiddleUrl) {
        this.coverMiddleUrl = coverMiddleUrl == null ? null : coverMiddleUrl.trim();
    }

    /**
     * 获取分享摘要
     *
     * @return abstract_info - 分享摘要
     */
    public String getAbstractInfo() {
        return abstractInfo;
    }

    /**
     * 设置分享摘要
     *
     * @param abstractInfo 分享摘要
     */
    public void setAbstractInfo(String abstractInfo) {
        this.abstractInfo = abstractInfo == null ? null : abstractInfo.trim();
    }

    /**
     * 获取讲师id
     *
     * @return lecturer_id - 讲师id
     */
    public Integer getLecturerId() {
        return lecturerId;
    }

    /**
     * 设置讲师id
     *
     * @param lecturerId 讲师id
     */
    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    /**
     * 获取内容标签
     *
     * @return content - 内容标签
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容标签
     *
     * @param content 内容标签
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取是否删除 1是 0否
     *
     * @return is_drop - 是否删除 1是 0否
     */
    public String getIsDrop() {
        return isDrop;
    }

    /**
     * 设置是否删除 1是 0否
     *
     * @param isDrop 是否删除 1是 0否
     */
    public void setIsDrop(String isDrop) {
        this.isDrop = isDrop == null ? null : isDrop.trim();
    }

    /**
     * 获取创建时间
     *
     * @return ctime - 创建时间
     */
    public Integer getCtime() {
        return ctime;
    }

    /**
     * 设置创建时间
     *
     * @param ctime 创建时间
     */
    public void setCtime(Integer ctime) {
        this.ctime = ctime;
    }

    /**
     * 获取修改时间
     *
     * @return mtime - 修改时间
     */
    public Integer getMtime() {
        return mtime;
    }

    /**
     * 设置修改时间
     *
     * @param mtime 修改时间
     */
    public void setMtime(Integer mtime) {
        this.mtime = mtime;
    }

    /**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Integer getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Integer getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取显示状态 1显示 0禁用
     *
     * @return state - 显示状态 1显示 0禁用
     */
    public String getState() {
        return state;
    }

    /**
     * 设置显示状态 1显示 0禁用
     *
     * @param state 显示状态 1显示 0禁用
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 获取点击量
     *
     * @return popularity_index - 点击量
     */
    public Integer getPopularityIndex() {
        return popularityIndex;
    }

    /**
     * 设置点击量
     *
     * @param popularityIndex 点击量
     */
    public void setPopularityIndex(Integer popularityIndex) {
        this.popularityIndex = popularityIndex;
    }

    /**
     * 获取发布时间
     *
     * @return published_time - 发布时间
     */
    public Integer getPublishedTime() {
        return publishedTime;
    }

    /**
     * 设置发布时间
     *
     * @param publishedTime 发布时间
     */
    public void setPublishedTime(Integer publishedTime) {
        this.publishedTime = publishedTime;
    }

    /**
     * 获取用于前端判断的字段
     *
     * @return add_id - 用于前端判断的字段
     */
    public Integer getAddId() {
        return addId;
    }

    /**
     * 设置用于前端判断的字段
     *
     * @param addId 用于前端判断的字段
     */
    public void setAddId(Integer addId) {
        this.addId = addId;
    }

    /**
     * 获取直播介绍
     *
     * @return introduce - 直播介绍
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * 设置直播介绍
     *
     * @param introduce 直播介绍
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}
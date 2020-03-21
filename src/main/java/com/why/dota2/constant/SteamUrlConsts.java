package com.why.dota2.constant;

/**
 * 数据请求URL常量
 * 参考网站：
 */
public class SteamUrlConsts {
    // 获取所有英雄数据
    public static final String GET_HEROES = "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v1" +
            "?key={key}&language={language}";
    // 获取所有物品信息
    public static final String GET_ITEMS = "https://api.steampowered.com/IEconDOTA2_570/GetGameItems/v1" +
            "?key={key}&language={language}";

    // 获取最新25场比赛记录
    public static final String GET_MATCH_HISTORY = "https://api.steampowered.com/IEconDOTA2_570/GetMatchHistory/V001" +
            "?key={key}";
    // 获取从指定比赛开始的最新25场比赛记录
    public static final String GET_MATCH_HISTORY_BY_START_MATCH = "https://api.steampowered.com/IEconDOTA2_570/GetMatchHistory/V001" +
            "?key={key}&start_match_id={start_match_id}";
    // 获取指定比赛的详细信息
    public static final String GET_MATCH_DETAILS = "https://api.steampowered.com/IEconDOTA2_570/GetMatchDetails/V001" +
            "?key={key}&match_id={match_id}";

    // 获取所有API信息
    public static final String GET_ALL_API = "http://api.steampowered.com/ISteamWebAPIUtil/GetSupportedAPIList/v0001" +
            "?key={key}";
}

package com.why.dota2.boot.constant;

/**
 * 数据请求URL常量
 * 参考网站：https://wiki.teamfortress.com/wiki/WebAPI#Dota_2
 */
public class SteamApiConsts {
    // 获取所有英雄数据
    public static final String GET_HEROES = "https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v1";
    // 获取所有物品信息
    public static final String GET_ITEMS = "https://api.steampowered.com/IEconDOTA2_570/GetGameItems/v1";

    // 获取最新25场比赛记录
    public static final String GET_MATCH_HISTORY = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001";
    // 获取指定比赛的详细信息
    public static final String GET_MATCH_DETAILS = "https://api.steampowered.com/IIDOTA2Match_570/GetMatchDetails/V001";
    // 获取所有API信息
    public static final String GET_ALL_API = "http://api.steampowered.com/ISteamWebAPIUtil/GetSupportedAPIList/v0001" ;


    public static final String PARAM_KEY = "key";
    public static final String PARAM_LANGUAGE = "language";
    public static final String PARAM_MATCHES_REQUESTED = "matches_requested";
}

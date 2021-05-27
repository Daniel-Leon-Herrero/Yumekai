package net.codefastly.yumekai.utilities

import net.codefastly.yumekai.fragments.Calendar.CalendarViewModel
import net.codefastly.yumekai.fragments.News.NewsViewModel
import net.codefastly.yumekai.fragments.Recent.RecentViewModel
import net.codefastly.yumekai.repository.online.repositoryAPI

private lateinit var calendarViewModel: CalendarViewModel
private lateinit var recentViewModel: RecentViewModel
private lateinit var newsViewModel: NewsViewModel
private lateinit var repo: repositoryAPI

fun setRepoAPI(r: repositoryAPI){
    repo = r
}

fun getRepoAPI(): repositoryAPI{
    return repo
}

fun setCalendarViewModel(cVM: CalendarViewModel){
    calendarViewModel = cVM
}

fun getCalendarViewModel(): CalendarViewModel{
    return calendarViewModel
}

fun setRecentsViewModel(rVM: RecentViewModel){
    recentViewModel = rVM
}

fun getRecentsViewModel(): RecentViewModel{
    return recentViewModel
}

fun setNewsViewModel(nVM: NewsViewModel){
    newsViewModel = nVM
}

fun getNewsViewModel(): NewsViewModel{
    return newsViewModel
}
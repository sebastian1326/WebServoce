package com.lopezing.webserviceram.server.RickAndMortyRepository

import com.lopezing.webserviceram.server.RickAndMorty
var page=1
class RickAndMortyRepository {

    suspend fun getPersons()=RickAndMorty.retrofit.getPersons(page)
    fun replacePage(){
        page++
    }
}
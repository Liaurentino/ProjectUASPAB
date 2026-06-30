package com.example.test.data

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {

    private const val SUPABASE_URL = "https://ertrszvfyaenimqhmpbg.supabase.co"
    private const val SUPABASE_ANON_KEY = "sb_publishable_VH0Bm7EKyB-kvDXmMzfQ9A_Inme8kCm"

    const val GOOGLE_WEB_CLIENT_ID = "576020829014-ec54lc9i21lojhqigo5l5fjf3od2f0in.apps.googleusercontent.com"

    val client = createSupabaseClient(
        supabaseUrl = SUPABASE_URL,
        supabaseKey = SUPABASE_ANON_KEY
    ) {
        install(Postgrest)
        install(Auth)
        install(Storage)
    }
}
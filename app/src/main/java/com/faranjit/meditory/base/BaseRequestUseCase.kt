package com.faranjit.meditory.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by Bulent Turkmen on 25.08.2021.
 *
 * Network istekleri icin olusturulan base usecase sinifi.
 * Her usecase'in kendi aldigi parametreleri ve dondugu response vardir.
 * Bu bilgiler usecase olusturulurken base class'a verilmelidir.
 */
abstract class BaseRequestUseCase<Response, Params> {

    /**
     * Usecase'in yaptigi isi hazirlar, calismaya hazir hale getirir
     */
    abstract suspend fun buildUseCase(params: Params): Response

    /**
     * Usecase'i, verilen parametrelerse calistirir belirtilen tipte sonucu doner.
     */
    suspend fun execute(params: Params): Response = withContext(Dispatchers.IO) {
        buildUseCase(params)
    }
}
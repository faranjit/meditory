package com.faranjit.meditory.features.home.presentation

import androidx.lifecycle.Observer
import com.faranjit.meditory.BaseUnitTest
import com.faranjit.meditory.base.Executor
import com.faranjit.meditory.base.ResponseWrapper
import com.faranjit.meditory.base.SharedPrefs
import com.faranjit.meditory.features.home.data.HomeApi
import com.faranjit.meditory.features.home.data.HomeDataRepository
import com.faranjit.meditory.features.home.data.datasource.HomeLocalDataSource
import com.faranjit.meditory.features.home.data.datasource.HomeRemoteDataSource
import com.faranjit.meditory.features.home.data.response.HomeResponse
import com.faranjit.meditory.features.home.data.response.Image
import com.faranjit.meditory.features.home.data.response.Meditation
import com.faranjit.meditory.features.home.data.response.Story
import com.faranjit.meditory.features.home.domain.GetHomeData
import com.faranjit.meditory.features.home.domain.GetUsername
import com.faranjit.meditory.features.home.domain.HomeRepository
import com.faranjit.meditory.features.home.presentation.model.MeditationModel
import com.faranjit.meditory.features.home.presentation.model.StoryModel
import com.faranjit.meditory.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import kotlin.test.assertEquals
import kotlin.test.assertFalse

/**
 * Created by Bulent Turkmen on 25.08.2021.
 */
class HomeViewModelTest : BaseUnitTest(), KoinTest {

    val viewModel: HomeViewModel by inject()
    val getHomeData: GetHomeData by inject()
    val getUsername: GetUsername by inject()

    @Mock
    private lateinit var sharedPrefs: SharedPrefs

    @Mock
    private lateinit var homeApi: HomeApi

    @Mock
    private lateinit var executor: Executor

    @Mock
    private lateinit var remoteDataSource: HomeRemoteDataSource

    @Mock
    private lateinit var meditationsObserver: Observer<List<MeditationModel>>

    @Mock
    private lateinit var storiesObserver: Observer<List<StoryModel>>

    @Before
    fun setup() {
        startKoin {
            modules(
                module {
                    factory { HomeLocalDataSource(sharedPrefs) }
                    factory { remoteDataSource }
                    factory<HomeRepository> { HomeDataRepository(get(), get()) }
                    factory { GetHomeData(get()) }
                    factory { GetUsername(get()) }
                    viewModel { HomeViewModel(get(), get()) }
                }
            )
        }
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun `should formatUsername trigger bannerText`() {
        // Given
        val template = "Hello, %s"

        // When
        `when`(sharedPrefs.getString(anyString(), anyString())).thenReturn("faranjit")
        viewModel.formatUsername(template)

        // Then
        assertEquals("Hello, faranjit", viewModel.bannerText.get())
    }

    @Test
    fun `should getHomeData triggers liveDatas`() = runBlocking {
        // Given
        viewModel.meditationsLiveData.observeForever(meditationsObserver)
        viewModel.storiesLiveData.observeForever(storiesObserver)

        val homeResponse = HomeResponse(
            isBannerEnabled = true,
            meditations = listOf(
                Meditation(
                    "title", "subtitle", Image("small", "large"), "date", "content"
                )
            ),
            stories = listOf(
                Story(
                    "name", "category", Image("small", "large"), "date", "text"
                )
            )
        )

        // When
        `when`(getHomeData.buildUseCase(Unit)).thenReturn(ResponseWrapper.Success(homeResponse))
        viewModel.getHomeData()

        // Then
        assertFalse(viewModel.meditationsLiveData.getOrAwaitValue().isNullOrEmpty())
        assertFalse(viewModel.storiesLiveData.getOrAwaitValue().isNullOrEmpty())

        viewModel.meditationsLiveData.removeObserver(meditationsObserver)
        viewModel.storiesLiveData.removeObserver(storiesObserver)

    }
}
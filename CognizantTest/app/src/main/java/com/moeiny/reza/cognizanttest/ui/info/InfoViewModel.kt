package com.moeiny.reza.cognizanttest.ui.info

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moeiny.reza.cognizanttest.data.inforepository.InfoRepository
import com.moeiny.reza.cognizanttest.data.model.uimodel.ShowInfoModel
import com.moeiny.reza.cognizanttest.core.result.Result


class InfoViewModel(private val infoRepository: InfoRepository) : ViewModel() {

    val infoLiveData = MutableLiveData<List<ShowInfoModel>>()

    val loadingLiveData = MutableLiveData<Boolean>()

    val errorLiveData = MutableLiveData<Boolean>()

    /**
     * getAllData function:fetching data from repository by considering 3 state of onSuccess,onError,onLoading
     */

    fun getAllInfo() {
        infoRepository.fetchInfo { result ->
            if (result is Result.Success) {

                /**
                 * Map  Data from API model to UI Model
                 */
                val infoList = result.data.rows.map { info ->
                    ShowInfoModel(
                        title = info.title.orEmpty(),
                        description = info.description?.orEmpty(),
                        imageHref = info.imageHref?.orEmpty()
                    )
                }
                infoLiveData.postValue(infoList)
            } else if (result is Result.Loading) {
                loadingLiveData.postValue(true)
            } else if (result is Result.Error) {
                errorLiveData.postValue(true)
            }
        }
    }

}
package com.saifurs.user.home.model


import com.saifurs.user.home.model.dataModel.BannerModel
import com.saifurs.user.home.model.dataModel.BookListModel
import com.saifurs.user.home.model.dataModel.CategoryModel
import com.saifurs.user.home.model.dataModel.CourseListModel
import com.saifurs.user.network.ApiClient
import com.saifurs.user.network.ApiInterface
import com.saifurs.user.network.RequestCompleteListener
import com.saifurs.user.utils.CommonMethods
import com.saifurs.user.utils.StaticKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeModel {

    companion object {

        private val apiInterface: ApiInterface =
            ApiClient().getClient.create(ApiInterface::class.java)

        fun getCategory(requestCompleteListener: RequestCompleteListener<CategoryModel>) {
            val call: Call<CategoryModel> = apiInterface.getAllCategory(StaticKey.COMPANY_ID)
            call.enqueue(object : Callback<CategoryModel> {
                override fun onResponse(
                    call: Call<CategoryModel>,
                    response: Response<CategoryModel>
                ) {
                    if (response.code() == 200) {
                        response.body()?.let { requestCompleteListener.onSuccess(it) }
                    } else {
                        requestCompleteListener.onFailed(response.message())
                    }
                }

                override fun onFailure(call: Call<CategoryModel>, t: Throwable) {
                    requestCompleteListener.onFailed(CommonMethods.exceptionHandler(t))
                }

            })
        }

        fun getCourseList(
            categoryID: Int?,
            isFeatured: String?,
            sorting: String?,
            callback: RequestCompleteListener<CourseListModel>
        ) {
            val call: Call<CourseListModel> =
                apiInterface.getCourseList(categoryID, isFeatured,sorting, StaticKey.COMPANY_ID)
            call.enqueue(object : Callback<CourseListModel> {
                override fun onResponse(
                    call: Call<CourseListModel>,
                    response: Response<CourseListModel>
                ) {
                    if (response.code() == 200) {
                        response.body()?.let { callback.onSuccess(it) }
                    } else {
                        callback.onFailed(response.message())
                    }
                }

                override fun onFailure(call: Call<CourseListModel>, t: Throwable) {
                    callback.onFailed(CommonMethods.exceptionHandler(t))
                }

            })
        }

        fun getBanner(callback: RequestCompleteListener<BannerModel>) {
            val call: Call<BannerModel> = apiInterface.getHomeBanner(StaticKey.COMPANY_ID)
            call.enqueue(object : Callback<BannerModel> {
                override fun onResponse(call: Call<BannerModel>, response: Response<BannerModel>) {
                    if (response.code() == 200) {
                        response.body()?.let { callback.onSuccess(it) }
                    } else {
                        callback.onFailed(response.message())
                    }
                }

                override fun onFailure(call: Call<BannerModel>, t: Throwable) {
                    callback.onFailed(CommonMethods.exceptionHandler(t))
                }

            })
        }

        fun getBookList(callback: RequestCompleteListener<BookListModel>) {
            val call: Call<BookListModel> = apiInterface.getBookList(StaticKey.COMPANY_ID)
            call.enqueue(object : Callback<BookListModel> {
                override fun onResponse(
                    call: Call<BookListModel>,
                    response: Response<BookListModel>
                ) {
                    if (response.code() == 200) {
                        response.body()?.let { callback.onSuccess(it) }
                    } else {
                        callback.onFailed(response.message())
                    }
                }

                override fun onFailure(call: Call<BookListModel>, t: Throwable) {
                    callback.onFailed(CommonMethods.exceptionHandler(t))
                }

            })

        }

        fun getEbookList(callback: RequestCompleteListener<BookListModel>) {
            val call: Call<BookListModel> = apiInterface.getEbookList(StaticKey.COMPANY_ID)
            call.enqueue(object : Callback<BookListModel> {
                override fun onResponse(
                    call: Call<BookListModel>,
                    response: Response<BookListModel>
                ) {
                    if (response.code() == 200) {
                        response.body()?.let { callback.onSuccess(it) }
                    } else {
                        callback.onFailed(response.message())
                    }
                }

                override fun onFailure(call: Call<BookListModel>, t: Throwable) {
                    callback.onFailed(CommonMethods.exceptionHandler(t))
                }

            })

        }

    }


}
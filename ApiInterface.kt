package com.saifurs.user.network

import com.google.gson.JsonObject
import com.saifurs.user.blog.model.dataModel.BlogDataModel
import com.saifurs.user.blog.model.dataModel.BlogDetailsDataModel
import com.saifurs.user.books.model.datamodel.*
import com.saifurs.user.course.model.dataModel.CourseBuyDetailsModel
import com.saifurs.user.course.model.dataModel.CourseCommentModel
import com.saifurs.user.course.model.dataModel.CourseEnrollDataModel
import com.saifurs.user.course.model.dataModel.CoursePagingModel
import com.saifurs.user.course.myCourseDetails.model.dataModel.*
import com.saifurs.user.event.model.dataModel.EventPagingModel
import com.saifurs.user.event.model.dataModel.EventDataModel
import com.saifurs.user.event.model.dataModel.EventJoinDataModel
import com.saifurs.user.home.model.dataModel.*
import com.saifurs.user.payment.model.dataModel.*
import com.saifurs.user.player.model.PostProgressResponse
import com.saifurs.user.profile.model.datamodel.PhotoM
import com.saifurs.user.profile.model.datamodel.ProfileDataModel
import com.saifurs.user.profile.model.datamodel.ProfileUpdateM
import com.saifurs.user.purchase.model.dataModel.PurchaseDetailsM
import com.saifurs.user.purchase.model.dataModel.PurchaseHistoryDataModel
import com.saifurs.user.register.model.datamodel.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    /**Network calling place for Tarikul Islam**/
    //login
    @Headers("Content-Type: application/json")
    @POST("v1/login")
    fun login(@Body body: JsonObject?): Call<SignInM>

    //Sign up
    @Headers("Content-Type: application/json")
    @POST("v1/student-registration-store")
    fun signup(@Body body: JsonObject?): Call<RegM>

    //change password
    @Headers("Content-Type: application/json")
    @POST("v1/change-password/{user_id}")
    fun changePassword(
        @Body body: JsonObject?,
        @Header("Authorization") authToken: String,
        @Path("user_id") userId: String
    ): Call<PostRespM>

    //reset password
    @Headers("Content-Type: application/json")
    @POST("v1/reset-password")
    fun resetPassword(@Body body: JsonObject?): Call<ResetPassM>

    //otp
    @GET("v1/user-otp")
    fun userOtp(@Query("mobile_number") mobileNumber: String): Call<OTPM>

    //otp registration
    @GET("v1/user-otp-registration")
    fun userRegOtp(@Query("mobile_number") mobileNumber: String): Call<OTPM>

    //otp verify
    @GET("v1/user-otp-verify")
    fun userOtpVerify(
        @Query("mobile_number") mobileNumber: String,
        @Query("otp_number") otpNumber: String
    ): Call<OTPVerifyM>

    //logout
    @GET("v1/user-api-logout")
    fun logout(@Header("Authorization") authToken: String): Call<PostRespM>

//    //list type
//    @GET("v1/married-status-list")
//    fun marriedStatusList(): Call<List<MarriedSM>>

    @GET("v1/get-state-list")
    fun getDivisionList(): Call<DivisionLM>

    @GET("v1/get-city-list")
    fun getCityListByDivId(@Query("state_id") stateId: String): Call<CityLM>

    @GET("v1/get-branch-list")
    fun getBranchByCompanyId(@Query("company_id") companyId: String): Call<BranchLM>

    @GET("v1/find-user-name")
    fun findUserName(@Query("username") userName: String): Call<PostRespM>

    @GET("v1/find-user-mobile-number")
    fun findUserMobileNumber(@Query("mobile_phone") mobilePhone: String): Call<PostRespM>

    @GET("v1/find-user-have-email")
    fun findUserEmail(@Query("email") email: String): Call<PostRespM>

    //profile upload
    @Multipart
    @POST("v1/student-profile-image")
    fun uploadProfilePhoto(
        @Part imageFile: MultipartBody.Part,
        @Header("Authorization") authToken: String
    ): Call<PhotoM>

    //book details
    @GET("v1/get-book-detail/{id}")
    fun bookDetails(@Path("id") id: String): Call<BookDetailsM>

    // to comment
    @FormUrlEncoded
    @POST("v1/post-my-book-rating-comment-store")
    fun storeBookRatingComment(
        @Field("book_id") bookId: String,
        @Field("user_id") userId: String,
        @Field("book_rating") bookRatingValue: Float,
        @Field("book_comment") commentText: String,
        @Field("is_approved") isApproved: String,
        @Field("book_rating_comment_status") bookRatingStatus: String,
        @Header("Authorization") authToken: String
    ): Call<RatingBookM>

    // get all book rating commment list
    @GET("v1/get-book-rating-comments")
    fun getBookRatingComments(
        @Query("book_id") bookId: String,
        @Query("company_id") companyId: Int,
        @Query("page") page: Int,
        @Query("book_rating_by_id") sortKey: String
    ): Call<BookRatingList>

    // store course comment
    // to comment
    @FormUrlEncoded
    @POST("v1/post-my-course-rating-comment-store")
    fun storeCourseRatingComment(
        @Field("company_id") companyId: Int,
        @Field("user_id") userId: String,
        @Field("course_id") courseId: Int,
        @Field("course_rating_stars") courseRatingValue: Float,
        @Field("course_rating_feedback") commentText: String,
        @Field("is_approved") isApproved: String,
        @Field("course_rating_status") courseRatingStatus: String,
        @Header("Authorization") authToken: String
    ): Call<CourseCommentModel>

    //purchase details
    @GET("v1/get-purchase-details/{id}")
    fun purchaseDetails(
        @Path("id") id: Int,
        @Header("Authorization") authToken: String
    ): Call<PurchaseDetailsM>

    // free course enroll
    @FormUrlEncoded
    @POST("v1/post-course-user-registration")
    fun freeCourseEnroll(
        @Field("course_id") courseId: Int,
        @Field("batch_id") batchId: Int,
        @Field("user_id") userId: Int,
        @Header("Authorization") authToken: String
    ): Call<CourseEnrollDataModel>


    /**Network calling place for Sahin Safi**/

    @GET("v1/get-course-category-list")
    fun getAllCategory(@Query("company_id") company_id: Int): Call<CategoryModel>

    @GET("v1/get-course-list")
    fun getCourseList(
        @Query("course_category_id") categoryID: Int?,
        @Query("course_featured") isFeatured: String?,
        @Query("course_sorting") sorting: String?,
        @Query("company_id") companyID: Int
    ): Call<CourseListModel>

    @GET("v1/get-banner-list?company_id=1")
    fun getHomeBanner(@Query("company_id") companyId: Int): Call<BannerModel>

    @GET("v1/get-book-list")
    fun getBookList(@Query("company_id") companyId: Int): Call<BookListModel>

    @GET("v1//get-e-book-list")
    fun getEbookList(@Query("company_id") companyId: Int): Call<BookListModel>

    @FormUrlEncoded
    @POST("v1/post-ebook-user-registration")
    fun postFreeBook(
        @Field("book_id") bookId: Int,
        @Field("user_id") userID: Int,
        @Header("Authorization") token: String
    ): Call<FreeBookResponseModel>

    @GET("v1/get-book-list-pagination")
    fun searchBooks(
        @Query("company_id") companyId: Int,
        @Query("book_category_name") categoryName: String,
        @Query("sorting_order") sorting: String?,
        @Query("book_a_to_z_sorting_order") sortAToZ: String?,
        @Query("book_price_sorting_order") sortPrice: String?,
        //@Query("search_text") search: String,
        @Query("book_name") search: String,
        @Query("page") page: Int
    ): Call<BookPagingModel>

    @GET("v1/get-book-list-pagination?purchased_book_only=true&sorting_order=desc")
    fun getPurchasedBook(
        @Query("company_id") companyId: Int,
        @Query("user_id") userID: Int,
        @Query("page") page: Int,
        @Header("Authorization") token: String
    ): Call<BookPagingModel>

    @GET("v1/get-book-category-list")
    fun getBookCategory(@Query("company_id") companyId: Int): Call<BookCategoryModel>

    @GET("v1/get-course-list-pagination")
    fun searchCourse(
        @Query("company_id") companyId: Int,
        @Query("course_category_id") categoryID: Int?,
        @Query("course_sorting") sorting: String?,
        @Query("course_a_to_z_sorting") sortAToZ: String?,
        @Query("course_regular_price_sorting") sortPrice: String?,
        @Query("course_featured") isFeatured: String?,
        @Query("course_title_wild_card") search: String,
        @Query("page") page: Int
    ): Call<CoursePagingModel>

    @GET("v1/get-event-list-pagination?event_sorting=ASC")
    fun eventPaging(
        @Query("company_id") companyId: Int,
        @Query("user_id") userID: Int?,
        @Query("registered_event_only") registrationEvent: String?,
        @Query("page") page: Int
    ): Call<EventPagingModel>

    @GET("v1/get-my-course-assignment-list")
    fun getAssignmentList(
        @Query("course_id") courseID: Int?,
        @Query("company_id") companyId: Int?,
        @Query("page") page: Int?,
        @Header("Authorization") authToken: String
    ): Call<AssignPagingModel>

    @GET("v1/get-course-detail/{id}")
    fun getCourseDetails(
        @Path(
            value = "id",
            encoded = true
        ) courseId: Int
    ): Call<CourseBuyDetailsModel>

    @Multipart
    @POST("v1/post-my-course-assignment-store")
    fun postAssignment(
        @Query("company_id") companyID: Int,
        @Query("student_id") studentID: String,
        @Query("instructor_id") instructorID: Int,
        @Query("course_id") courseID: Int,
        @Query("announcement_id") announceID: Int,
        @Query("course_assignment_name") assignmentName: String,
        @Query("course_assignment_detail") assignmentDetails: String,
        @Part course_assignment_document: MultipartBody.Part,
        @Query("course_assignment_status") status: String,
        @Header("Authorization") token: String
    ): Call<AssignUpResponseModel>

    @GET("v1/get-course-rating-comments")
    fun getCourseComment(
        @Query("company_id") companyId: Int,
        @Query("course_id") courseId: Int,
        @Query("page") page: Int
    ): Call<CourseCommentModel>

    @GET("v1/get-ssl-commerce-credential")
    fun getSSLCredential(@Query("company_id") companyId: Int): Call<SSLCredentialModel>

    @GET("v1/post-sslcommerz-gateway-response")
    fun sendTransactionInfo(
        @Query("tran_id") tran_id: String,
        @Query("val_id") val_id: String,
        @Query("amount") amount: String,
        @Query("card_type") card_type: String,
        @Query("store_amount") store_amount: String,
        @Query("bank_tran_id") bank_tran_id: String,
        @Query("status") status: String,
        @Query("tran_date") tran_date: String,
        @Query("currency") currency: String,
        @Query("card_issuer") card_issuer: String,
        @Query("card_brand") card_brand: String,
        @Query("card_sub_brand") card_sub_brand: String,
        @Query("card_issuer_country") card_issuer_country: String,
        @Query("card_issuer_country_code") card_issuer_country_code: String,
        @Query("store_id") store_id: String,
        @Query("verify_sign") verify_sign: String,
        @Query("verify_key") verify_key: String,
        @Query("verify_sign_sha2") verify_sign_sha2: String,
        @Query("currency_type") currency_type: String,
        @Query("currency_amount") currency_amount: String,
        @Query("currency_rate") currency_rate: String,
        @Query("base_fair") base_fair: String,
        @Query("risk_level") risk_level: String,
        @Query("risk_title") risk_title: String
    ): Call<TranResponseModel>

    @POST("v1/post-cart-order-store")
    fun postInvoice(
        @Query("company") companyId: Int,
        @Query("branch") branch: String,
        @Query("entry_date") date: String,
        @Query("user") userID: Int,
        @Query("phone") phone: String,
        @Query("email") email: String,
        @Query("name") name: String,
        @Query("ship_address") address: String,
        @Body invoice: InvoiceItemModel,
        @Query("coupon_code") coupon: String,
        @Query("shipping_cost") shippingCost: String,
        @Query("notes") notes: String,
        @Query("source_type") sourceType: String,
        @Header("Authorization") token: String
    ): Call<InvoiceResponseModel>


    @GET("v1/get-my-course-chapter-list?course_chapter_by_id=asc&is_paginate=no")
    fun getCourseChapter(
        @Query("company_id") companyId: Int,
        @Query("course_id") courseId: Int,
        @Query("page") page: Int,
        @Header("Authorization") token: String
    ): Call<ChapterModel>

    @FormUrlEncoded
    @POST("v1/post-my-course-progress-store")
    fun postCourseProgress(
        @Field("student_id") studentID: String,
        @Field("course_class_id") classId: Int,
        @Header("Authorization") token: String
    ): Call<PostProgressResponse>

    /**Network calling place for Sabiruzzaman**/

    // profile view
    @GET("v1/profile-show")
    fun getProfile(@Header("Authorization") authToken: String): Call<ProfileDataModel>

    // profile update
    @Headers("Content-Type: application/json")
    @POST("v1/student-profile-update/{user_id}")
    fun profileUpdate(
        @Body body: JsonObject?,
        @Path("user_id") userId: String,
        @Header("Authorization") authToken: String
    ): Call<ProfileUpdateM>

    @GET("v1/get-event-detail/{id}")
    fun getEvent(
        @Path("id") id: String,
        @Header("Authorization") authToken: String
    ): Call<EventDataModel>

    @POST("v1/post-event-invitation")
    fun joinEvent(
        @Header("Authorization") authToken: String,
        @Query("event_id") eventId: String
    ): Call<EventJoinDataModel>

    @GET("v1/get-announcement-list-pagination")
    fun getAnnouncement(
        @Query("course_id") courseId: Int,
        @Query("company_id") companyId: Int,
        @Query("announcement_type") type: String?,
        @Query("page") page: Int,
        @Header("Authorization") authToken: String
    ): Call<AnnouncementPaginationDataM>

    @GET("v1/get-my-course-list")
    fun getMyCourse(
        @Query("company_id") companyId: Int,
        @Header("Authorization") authToken: String
    ): Call<MyCourseDataModel>

    @GET("v1/get-course-quiz-list-pagination")
    fun getQuiz(
        @Query("company_id") companyId: Int,
        @Query("course_id") courseId: Int,
        @Query("page") page: Int,
        @Header("Authorization") authToken: String
    ): Call<QuizListPaginationDataM>

    @POST("v1/get-course-quiz-list-pagination")
    fun courseEnroll(
        @Query("course_id") courseId: Int,
        @Query("batch_id") batchId: Int,
        @Query("user_id") userId: Int,
        @Header("Authorization") authToken: String
    ): Call<CourseEnrollDataModel>

    @GET("v1/get-purchase-history-pagination")
    fun getPurchaseHistory(
        @Query("page") page: Int,
        @Header("Authorization") authToken: String
    ): Call<PurchaseHistoryDataModel>

    @GET("v1/get-blog-list-pagination")
    fun getBlog(
        @Query("page") page: Int
    ): Call<BlogDataModel>


    @GET("v1/get-blog-list")
    fun getBlogDetails(
        @Query("id") id: Int
    ): Call<BlogDetailsDataModel>



}
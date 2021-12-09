package com.example.retrofit_airways.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_airways.adapter.MyAdapter
import com.example.retrofit_airways.databinding.ActivityMainBinding
import com.example.retrofit_airways.model.ModelResponse
import com.example.retrofit_airways.repository.RetroInstance
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var postList:List<ModelResponse>
    lateinit var list:List<ModelResponse>
    lateinit var myAdapter:MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val rf= Retrofit.Builder()
                .baseUrl(RetroInstance.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient()!!.build())
                .build()

        val api:RetroInstance=rf.create(RetroInstance::class.java)
        val call: Call<List<ModelResponse>> =  api.posts

        call.enqueue(object : Callback<List<ModelResponse>> {
            override fun onResponse(call: Call<List<ModelResponse>>, response: Response<List<ModelResponse>>) {
                postList = response.body() as List<ModelResponse>
                val post = arrayOfNulls<String>(postList.size)
                list = postList
                for (i in postList.indices) {
                    post[i] = postList[i].id.toString()
                }
                /*val adapter = ArrayAdapter<String>(applicationContext, R.layout.support_simple_spinner_dropdown_item, post)
                binding.listView.adapter = adapter*/

                val layoutManager = GridLayoutManager(applicationContext, 2)
                layoutManager.orientation = RecyclerView.VERTICAL
                binding.recyclerView.setHasFixedSize(true)
                binding.recyclerView.layoutManager = layoutManager
                myAdapter = MyAdapter(postList, applicationContext,supportFragmentManager)
                binding.recyclerView.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<ModelResponse>>, t: Throwable) {

            }

        })

        binding.cartId.setOnClickListener {
            val intent= Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getUnsafeOkHttpClient(): OkHttpClient.Builder? {
        return try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(
                    object : X509TrustManager {
                        @SuppressLint("TrustAllX509TrustManager")
                        @Throws(CertificateException::class)
                        override fun checkClientTrusted(
                                chain: Array<X509Certificate>,
                                authType: String
                        ) {
                        }

                        @SuppressLint("TrustAllX509TrustManager")
                        @Throws(CertificateException::class)
                        override fun checkServerTrusted(
                                chain: Array<X509Certificate>,
                                authType: String
                        ) {
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> {
                            return arrayOf()
                        }
                    }
            )

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory
            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }
            builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
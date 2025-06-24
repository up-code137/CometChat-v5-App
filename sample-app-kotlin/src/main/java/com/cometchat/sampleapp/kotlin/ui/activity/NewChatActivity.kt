package com.cometchat.sampleapp.kotlin.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cometchat.chat.core.GroupsRequest.GroupsRequestBuilder
import com.cometchat.chat.core.UsersRequest.UsersRequestBuilder
import com.cometchat.chat.models.Group
import com.cometchat.chatuikit.shared.interfaces.OnItemClick
import com.cometchat.sampleapp.kotlin.R
import com.cometchat.sampleapp.kotlin.databinding.ActivityNewChatBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson

class NewChatActivity : AppCompatActivity() {
    private var binding: ActivityNewChatBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewChatBinding.inflate(
            layoutInflater
        )
        setContentView(binding!!.root)

        binding!!.users.toolbarVisibility = View.GONE
        binding!!.groups.toolbarVisibility = View.GONE
        binding!!.users.separatorVisibility = View.GONE
        binding!!.groups.separatorVisibility = View.GONE
        binding!!.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab.position == 0) {
                    binding!!.users.visibility = View.VISIBLE
                    binding!!.groups.visibility = View.GONE
                } else {
                    binding!!.users.visibility = View.GONE
                    binding!!.groups.visibility = View.VISIBLE
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) { // Optional logic for reselected tab
            }
        })

        binding!!.tabLayout.addTab(
            binding!!.tabLayout
                .newTab()
                .setText(resources.getString(R.string.app_bottom_nav_users))
        )
        binding!!.tabLayout.addTab(
            binding!!.tabLayout
                .newTab()
                .setText(resources.getString(R.string.app_bottom_nav_groups))
        )
        binding!!.tabLayout
            .getTabAt(0)!!
            .select()
        binding!!.users.setUsersRequestBuilder(
            UsersRequestBuilder()
                .hideBlockedUsers(true)
                .setLimit(30)
        )
        binding!!.users.setOnItemClick { view, position, user ->
            val intent = Intent(this@NewChatActivity, MessagesActivity::class.java)
            intent.putExtra(getString(R.string.app_user), Gson().toJson(user))
            startActivity(intent)
            finish()
        }

        binding!!.groups.setGroupsRequestBuilder(
            GroupsRequestBuilder()
                .joinedOnly(true)
                .setLimit(30)
        )
        binding!!.groups.setOnItemClick(object : OnItemClick<Group?> {
            override fun click(
                view: View?,
                position: Int,
                group: Group?

            ) {
                val intent = Intent(this@NewChatActivity, MessagesActivity::class.java)
                intent.putExtra(getString(R.string.app_group), Gson().toJson(group))
                startActivity(intent)
                finish()
            }
        })

        binding!!.ivBack.setOnClickListener { v: View? -> finish() }
    }
}
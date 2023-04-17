package com.abhishek.jarvish.db

import org.junit.Assert.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.abhishek.jarvish.db.Repository
import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.db.table.UserDetailWithRelations
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoryTest {

    private lateinit var repository: Repository

    @Before
    fun setup() {

    }

    }
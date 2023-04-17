package com.abhishek.jarvish.db.dao
import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.abhishek.jarvish.db.AppDatabase
import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.db.table.UserDetailTable
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.*

class UserDetailDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDetailDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).allowMainThreadQueries().build()
        userDao = db.userDetailDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsertUserDetail() = runBlocking{
        val userDetail = UserDetailTable("1", "John", "Doe", "john.doe@example.com", Date())
        userDao.insertUserDetail(userDetail)
        val userDetailFromDb = userDao.getUserDetailById(1)
        assertEquals(userDetailFromDb, userDetail)
    }

    @Test
    fun testInsertMobileNo() = runBlocking{
        val mobileNo = MobileNo("1", "1234567890", "1")
        userDao.insertMobileNo(mobileNo)
        val mobileNoFromDb = userDao.getMobileNumbersByUserId(1)
        assertEquals(mobileNoFromDb.value?.get(0) ?: 0, mobileNo)
    }

    @Test
    fun testInsertAddress() = runBlocking{
        val address = Address("1", "123 Main St", "Apt 2", 37, "CA", "12345", "1")
        userDao.insertAddress(address)
        val addressFromDb = userDao.getAddressesByUserId(1)
        assertEquals(addressFromDb.value?.get(0) ?:0, address)
    }

    @Test
    fun testInsertEducation() = runBlocking{
        val education = Education("1", "B.S. Computer Science", "Stanford University", 2010, 2014, "1","")
        userDao.insertEducation(education)
        val educationFromDb = userDao.getEducationDetailsByUserId(1)
        assertEquals(educationFromDb.value?.get(0) ?:0, education)
    }

    @Test
    fun testInsertUserDetailWithRelations() = runBlocking {
        val userDetail = UserDetailTable("1", "John", "Doe", "john.doe@example.com",Date())
        val mobileNoList = listOf(
            MobileNo("1", "1234567890", "1"),
            MobileNo("2", "0987654321", "1")
        )
        val addressList = listOf(
            Address("1", "123 Main St", "Apt 2", 2002, "CA", "12345", "1"),
            Address("2", "456 Oak Ave", "", 29929, "CA", "54321", "1")
        )
        val educationList = listOf(
            Education("1", "B.S. Computer Science", "Stanford University", 2010, 2014, "1",""),
            Education("2", "M.S. Computer Science", "MIT", 2014, 2016, "1","")
        )
        userDao.insertUserDetailWithRelations(userDetail, mobileNoList, addressList, educationList)
        val userDetailFromDb = userDao.getUserDetailById(1)
        val mobileNoFromDb = userDao.getMobileNumbersByUserId(1)
        val addressFromDb = userDao.getAddressesByUserId(1)
        val educationFromDb = userDao.getEducationDetailsByUserId(1)
        assertEquals(userDetailFromDb, userDetail)
        assertEquals(mobileNoFromDb, mobileNoList)
        assertEquals(addressFromDb, addressList)
        assertEquals(educationFromDb, educationList)
    }


    @Test
    fun testInsertAndGetUserDetailById() {
        runBlocking {
            val userDetail = UserDetailTable(
                userId = "1",
                profileImage = "",
                firstName = "John",
                lastName = "Doe",
                dob = Date()
            )
            userDao.insertUserDetail(userDetail)
            val result = userDao.getUserDetailById(1).value
            assertEquals(userDetail, result)
        }
    }
}
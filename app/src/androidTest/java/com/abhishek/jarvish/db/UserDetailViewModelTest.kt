package com.abhishek.jarvish.db
import android.content.Context/*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule*/
import androidx.lifecycle.MutableLiveData
import com.abhishek.jarvish.db.Repository
import com.abhishek.jarvish.db.table.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test/*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations*/

class UserDetailViewModelTest {
/*
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: Repository

    lateinit var viewModel: UserDetailViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = UserDetailViewModel(repository)
    }

    @Test
    fun `insert user detail should call repository insert user data`() {
        val context = Mockito.mock(Context::class.java)
        val userDetailTable = createUserDetailTable()
        viewModel.insertData(context, userDetailTable)
        Mockito.verify(repository).insertUserData(context, userDetailTable)
    }

    @Test
    fun `get user list should call repository get user list`() {
        val context = Mockito.mock(Context::class.java)
        val userList = MutableLiveData<List<UserDetailWithRelations>>()
        Mockito.`when`(repository.getUserList(context)).thenReturn(userList)
        assertEquals(userList, viewModel.getUserList(context))
        Mockito.verify(repository).getUserList(context)
    }

    @Test
    fun `update user detail should call repository update user list`() {
        val context = Mockito.mock(Context::class.java)
        val userId = "1"
        val userDetailTable = createUserDetailTable()
        val value = UserDetailWithRelations(userDetailTable, listOf(), listOf(), listOf())
        viewModel.updateData(context, userId, value)
        Mockito.verify(repository).updateUserList(context, userId, value)
    }

    @Test
    fun `delete mobile number should call repository delete mobile no`() {
        val context = Mockito.mock(Context::class.java)
        val mobileNo = MobileNo("1", "1234567890", "1")
        viewModel.deleteMobileNo(context, mobileNo)
        Mockito.verify(repository).deleteMobileNo(context, mobileNo)
    }

    @Test
    fun `delete education should call repository delete education`() {
        val context = Mockito.mock(Context::class.java)
        val education = Education("1", "B.Tech", "Computer Science", 2015, 2019, "ABC College", "1")
        viewModel.deleteEducation(context, education)
        Mockito.verify(repository).deleteEducation(context, education)
    }

    @Test
    fun `delete address should call repository delete address`() {
        val context = Mockito.mock(Context::class.java)
        val address = Address("1", "Home", "123 Main St", "Apt 1", "City", "State", "Zip", "1")
        viewModel.deleteAddress(context, address)
        Mockito.verify(repository).deleteAddress(context, address)
    }

    private fun createUserDetailTable(): UserDetailWithRelations {
        val userDetail = UserDetailTable(
            "1", "profile.jpg", "John", "Doe", null
        )
        val mobileNumbers = listOf(
            MobileNo("1", "1234567890", "1"),
            MobileNo("2", "9876543210", "1")
        )
        val addresses = listOf(
            Address("1", "Home", "123 Main St", 23, "City", "State", "Zip", "1"),
            Address("2", "Work", "456 Main St", 23,"","","","d")
    }*/
}
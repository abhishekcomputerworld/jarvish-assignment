package com.abhishek.jarvish.viewModel

import com.abhishek.jarvish.db.table.Address
import com.abhishek.jarvish.db.table.Education
import com.abhishek.jarvish.db.table.MobileNo
import com.abhishek.jarvish.db.table.UserDetailWithRelations
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FillFormViewModelTest {
    @Mock
    private lateinit var fillFormViewModel: FillFormViewModel

    @Before
    fun setUp() {
        fillFormViewModel = FillFormViewModel()
    }

/*
    @Test
    fun `test getUserData`() {
        val expected = UserDetailWithRelations(
            UserDetail(userId = 1, name = "John Doe", email = "johndoe@example.com", profileImage = ""),
            arrayListOf(MobileNo(1, "1234567890")),
            arrayListOf(Address(1, "123 Main St", "San Francisco", "CA", "94110")),
            arrayListOf(Education(1, "BS in Computer Science", "Stanford University", 2022))
        )
        `when`(userDetailRepository.getUserData(1)).thenReturn(expected)

        fillFormViewModel.getUserData(1)

        assertEquals(expected, fillFormViewModel.getUserData().value)
    }
*/

    // Add more test cases for other ViewModel functions and LiveData variables.
}

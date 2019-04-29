package com.keiron.techtest.section.details.mapper

import com.keiron.babylonhealth.domain.common.model.CommentDetails
import com.keiron.babylonhealth.domain.common.model.PostDetails
import com.keiron.babylonhealth.domain.common.model.UserDetails
import com.keiron.techtest.section.details.model.CommentUiModel
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class PostDetailsToDetailsUiModelMapperTest {

    @Mock
    private lateinit var commentToCommentUiModelMapper: CommentToCommentUiModelMapper

    private lateinit var classUnderTest: PostDetailsToDetailsUiModelMapper
    @Before
    fun setUp() {
        initMocks(this)
        classUnderTest = PostDetailsToDetailsUiModelMapper(commentToCommentUiModelMapper)
    }

    @Test
    fun `given PostDetails when mapToPresentation then returns mapped MainUiModel`() {
        // Given
        val expectedPostTitle = "title"
        val expectedAuthorTitle = "username"
        val user = mock<UserDetails> {
            on { username } doReturn expectedAuthorTitle
        }
        val expectedBody = "body"
        val commentList = listOf(mock<CommentDetails>())
        val postDetails = mock<PostDetails> {
            on { title } doReturn expectedPostTitle
            on { body } doReturn expectedBody
            on { author } doReturn user
            on { comments } doReturn commentList
        }

        val expectedCommentUiModels = listOf<CommentUiModel>(mock())
        whenever(commentToCommentUiModelMapper.mapToPresentation(commentList)).thenReturn(expectedCommentUiModels)

        // When
        val uiModel = classUnderTest.mapToPresentation(postDetails)

        // Then
        with(uiModel) {
            assertEquals(expectedPostTitle, postTitle)
            assertEquals(expectedAuthorTitle, authorTitle)
            assertEquals(expectedBody, body)
            assertEquals(expectedCommentUiModels, comments)
        }
    }
}
package com.keiron.techtest.section.details.mapper

import com.keiron.babylonhealth.domain.posts.model.PostDetails
import com.keiron.library.common.mapper.BaseMapperToPresentation
import com.keiron.techtest.section.details.model.DetailsUiModel
import javax.inject.Inject

class PostDetailsToDetailsUiModelMapper @Inject constructor(private val commentToCommentUiModelMapper: CommentToCommentUiModelMapper) :
    BaseMapperToPresentation<PostDetails, DetailsUiModel>() {
    override fun mapToPresentation(toBeTransformed: PostDetails): DetailsUiModel {
        return DetailsUiModel(
            postTitle = toBeTransformed.title,
            body = toBeTransformed.body,
            authorTitle = toBeTransformed.author.username,
            comments = commentToCommentUiModelMapper.mapToPresentation(toBeTransformed.comments)
        )
    }
}
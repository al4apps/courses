package com.al4apps.courses.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.al4apps.courses.R
import com.al4apps.courses.databinding.CardCourseBinding
import com.al4apps.domain.models.Course
import com.bumptech.glide.Glide

class CoursesAdapter(
    private val onClick: (id: Int) -> Unit
) : ListAdapter<Course, CourseViewHolder>(CoursesDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardCourseBinding.inflate(layoutInflater, parent, false)
        return CourseViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class CoursesDiffUtil : DiffUtil.ItemCallback<Course>() {

        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
}

class CourseViewHolder(
    private val binding: CardCourseBinding,
    private val onClick: (id: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(course: Course) {
        with(binding) {
            binding.detailInfoTextView.setOnClickListener {
                onClick(course.id)
            }
            courseTitleTextView.text = course.title
            courseDescriptionTextView.text = course.text
            coursePriceTextView.text =
                itemView.resources.getString(R.string.course_price_text, course.price)
            ratingTextView.text = course.rate
            dateTextView.text = course.startDate
            if (course.hasLike) {
                markImageView.setImageResource(R.drawable.mark_filled)
            } else {
                markImageView.setImageResource(R.drawable.mark)
            }
            // Api не предоставляет image url
            Glide.with(itemView)
                .load(R.drawable.course_image)
                .into(mainImageView)
        }
    }
}
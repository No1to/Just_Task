package com.example.just_task.ui.onboarding.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.just_task.R
import com.example.just_task.databinding.ItemOnboardingBinding
import com.example.just_task.model.OnBoarding

class OnBoardingAdapter(private val onClick: () -> Unit) :
    Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {

    private val list = arrayListOf<OnBoarding>(
        OnBoarding(R.raw.ob_animation_1, OB_TITLE_1, OB_DESCRIPTION_1),
        OnBoarding(R.raw.ob_animation_2, OB_TITLE_2, OB_DESCRIPTION_2),
        OnBoarding(R.raw.ob_animation_3, OB_TITLE_3, OB_DESCRIPTION_3)
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            ItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class OnBoardingViewHolder(private val binding: ItemOnboardingBinding) :
        ViewHolder(binding.root) {

        fun bind(boarding: OnBoarding) = with(binding) {
            tvTitle.text = boarding.title
            tvDescription.text = boarding.description
            boarding.animation?.let { binding.animationView.setAnimation(it) }
//              Glide.with(binding.imgV).load(image).into(binding.imgV)
            btnStart.isVisible = adapterPosition == list.lastIndex
            tvSkip.isVisible = adapterPosition != list.lastIndex

            tvSkip.setOnClickListener {
                onClick()
            }
            btnStart.setOnClickListener {
                onClick()
            }
        }
    }

    companion object {
        private const val OB_TITLE_1 =
            "Организуйте свою жизнь с Just Task: просто, эффективно, удобно!"
        private const val OB_DESCRIPTION_1 =
            "Приветствуем в Just Task! Организуйте свою жизнь с легкостью. Создавайте, отслеживайте и завершайте задачи, чтобы достигнуть максимальной продуктивности."

        private const val OB_TITLE_2 =
            "Just Task - ваш личный ассистент по управлению задачами. Начните прямо сейчас!"
        private const val OB_DESCRIPTION_2 =
            "Добро пожаловать в мир Just Task! Управляйте своим временем с умом: добавляйте задачи, устанавливайте приоритеты и следите за своими достижениями."

        private const val OB_TITLE_3 =
            "Максимизируйте свою продуктивность с Just Task: интуитивный органайзер для достижения ваших целей."
        private const val OB_DESCRIPTION_3 =
            "Just Task - ваш личный органайзер! Забудьте о хаосе в повседневной жизни. Наше приложение поможет вам структурировать задачи и достигать целей."
    }

}
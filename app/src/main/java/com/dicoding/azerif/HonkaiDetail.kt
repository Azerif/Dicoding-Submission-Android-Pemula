package com.dicoding.azerif

import android.content.Intent
import android.graphics.Matrix
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.azerif.databinding.ActivityHonkaiDetailBinding

class HonkaiDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_PATH = "EXTRA_PATH"
        const val EXTRA_DESC = "EXTRA_DESC"
        const val EXTRA_IMG = "EXTRA_IMG"
        const val EXTRA_IMG_BG = "EXTRA_IMG_BG"
        const val EXTRA_RARITY_IMG = "EXTRA_RARITY_IMG"
        const val EXTRA_PATH_IMG = "EXTRA_PATH_IMG"
        const val EXTRA_TYPE_IMG = "EXTRA_TYPE_IMG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_honkai_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_backarrow)

        actionBar?.setDisplayHomeAsUpEnabled(true)
        val binding = ActivityHonkaiDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        val path = intent.getStringExtra(EXTRA_PATH)
        val desc = intent.getStringExtra(EXTRA_DESC)
        val charImg = intent.getIntExtra(EXTRA_IMG, 0)
        val charImgBG = intent.getIntExtra(EXTRA_IMG_BG, 0)
        val rarityImg = intent.getIntExtra(EXTRA_RARITY_IMG, 0)
        val pathImg = intent.getIntExtra(EXTRA_PATH_IMG, 0)
        val typeImg = intent.getIntExtra(EXTRA_TYPE_IMG, 0)

        binding.tvCharacterName.text = name
        binding.tvCharacterPath.text = path
        binding.tvCharacterDescription.text = desc
        binding.characterImage.setImageResource(charImg)
        binding.characterImage.setBackgroundResource(charImgBG)
        binding.rarityImage.setImageResource(rarityImg)
        binding.pathImage.setImageResource(pathImg)
        binding.typeImage.setImageResource(typeImg)

        binding.characterImage.post {
            applyTopCenterCrop(binding.characterImage)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareCharacterDetails()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun shareCharacterDetails() {
        val name = intent.getStringExtra(EXTRA_NAME)
        val desc = intent.getStringExtra(EXTRA_DESC)

        val shareText = "Check out this Honkai Star Rail character!\n" +
                "\nName: $name" +
                "\nDescription: $desc"

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareText)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun applyTopCenterCrop(imageView: ImageView) {
        val matrix = Matrix()

        val drawable = imageView.drawable ?: return
        val drawableWidth = drawable.intrinsicWidth
        val imageViewWidth = imageView.width

        // Calculate the scale required to fill the width and crop height
        val scale = imageViewWidth.toFloat() / drawableWidth.toFloat()

        // Apply a zoom factor
        val zoomFactor = 1.28f
        matrix.setScale(scale * zoomFactor, scale * zoomFactor)

        // Center the image horizontally and align it to the top
        val translationX = (imageViewWidth - drawableWidth * scale * zoomFactor) / 2
        matrix.postTranslate(translationX, 0f)

        imageView.imageMatrix = matrix
    }
}
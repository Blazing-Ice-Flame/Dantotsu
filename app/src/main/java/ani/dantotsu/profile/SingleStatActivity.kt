package ani.dantotsu.profile

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ani.dantotsu.databinding.ActivitySingleStatBinding
import ani.dantotsu.getThemeColor
import ani.dantotsu.initActivity
import ani.dantotsu.themes.ThemeManager
import ani.dantotsu.toast
import com.github.aachartmodel.aainfographics.aachartcreator.AAOptions

class SingleStatActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingleStatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeManager(this).applyTheme()
        initActivity(this)
        binding = ActivitySingleStatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chartOptions = intent.getParcelableExtra<AAOptions>(EXTRA_CHART_OPTIONS)

        if (chartOptions != null) {
            setupChart(chartOptions)
        } else {
            handleNoChartData()
        }
    }

    private fun setupChart(chartOptions: AAOptions) {
        chartOptions.chart?.backgroundColor = getThemeColor(android.R.attr.windowBackground)
        binding.chartView.aa_drawChartWithChartOptions(chartOptions)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    private fun handleNoChartData() {
        toast("No chart data available")
        finish()
    }

    companion object {
        private const val EXTRA_CHART_OPTIONS = "extra_chart_options"

        fun start(context: AppCompatActivity, chartOptions: AAOptions) {
            val intent = Intent(context, SingleStatActivity::class.java).apply {
                putExtra(EXTRA_CHART_OPTIONS, chartOptions)
            }
            context.startActivity(intent)
        }
    }
}

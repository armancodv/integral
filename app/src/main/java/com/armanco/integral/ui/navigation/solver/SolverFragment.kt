package com.armanco.integral.ui.navigation.solver

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.armanco.integral.R
import com.armanco.integral.data.models.Solver
import com.armanco.integral.utils.extensions.isTrigonometryOrTrigonometryPersian
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_solver.*

@AndroidEntryPoint
class SolverFragment: Fragment(R.layout.fragment_solver) {
    private val model: SolverViewModel by viewModels()
    private var adapter: SolverAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val solvers = if(isTrigonometryOrTrigonometryPersian) {
            listOf( Solver.TRIGONOMETRY, Solver.INVERSE_TRIGONOMETRY, Solver.INTEGRAL )
        } else {
            listOf( Solver.INTEGRAL, Solver.TRIGONOMETRY, Solver.INVERSE_TRIGONOMETRY )
        }
        adapter = SolverAdapter(this, solvers)
        viewPager?.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when(solvers.getOrNull(position)) {
                Solver.TRIGONOMETRY -> context?.getString(R.string.trigonometry)
                Solver.INVERSE_TRIGONOMETRY -> context?.getString(R.string.inverse_trigonometry)
                else -> context?.getString(R.string.integral)
            }
        }.attach()
        model.load()
    }
}
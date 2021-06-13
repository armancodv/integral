package com.armanco.integral.ui.navigation.solver

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.armanco.integral.data.models.Solver
import com.armanco.integral.ui.navigation.solver.page.IntegralSolverViewPager
import com.armanco.integral.ui.navigation.solver.page.InverseTrigonometrySolverViewPager
import com.armanco.integral.ui.navigation.solver.page.TrigonometrySolverViewPager

class SolverAdapter(fragment: Fragment, val solvers: List<Solver>) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = solvers.size

    override fun createFragment(position: Int): Fragment {
        return when(solvers.getOrNull(position)) {
            Solver.TRIGONOMETRY -> TrigonometrySolverViewPager()
            Solver.INVERSE_TRIGONOMETRY -> InverseTrigonometrySolverViewPager()
            else -> IntegralSolverViewPager()
        }
    }
}

package com.armanco.integral.utils.facade

import com.armanco.integral.data.models.User
import com.armanco.integral.data.repository.StatRepository
import com.armanco.integral.data.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.math.max

class InitFacade @Inject constructor(
    private val statRepository: StatRepository,
    private val userRepository: UserRepository,
) {

    suspend fun init() {
        AuthFacade.user?.let {
            updateUserLocal(it)?.let { it1 -> updateUserFirestore(it, it1) }
        }
    }

    private suspend fun getMessagingToken(): String? {
        return try {
            FirebaseMessaging.getInstance().token.await()
        } catch (exception: java.lang.Exception) {
            FirebaseCrashlytics.getInstance().recordException(exception)
            null
        }
    }

    private suspend fun updateUserLocal(firebaseUser: FirebaseUser): User? {
        try {
            val documentSnapshot = userRepository.get(firebaseUser.uid).await()
            val user: User =
                documentSnapshot.toObject<User>() ?: User.fromFirebaseUser(firebaseUser)
            user.let {
                val xp = max(statRepository.xp.first() ?: 0, it.xp ?: 0)
                val counterPlot = max(statRepository.counterPlot.first() ?: 0, it.counterPlot ?: 0)
                val counterCalculate =
                    max(statRepository.counterCalculate.first() ?: 0, it.counterCalculate ?: 0)
                val counterFormula =
                    max(statRepository.counterFormula.first() ?: 0, it.counterFormula ?: 0)
                val counterAd = max(statRepository.counterAd.first() ?: 0, it.counterAd ?: 0)

                statRepository.setXp(xp)
                statRepository.setCounterPlot(counterPlot)
                statRepository.setCounterCalculate(counterCalculate)
                statRepository.setCounterFormula(counterFormula)
                statRepository.setCounterAd(counterAd)

                it.xp = xp
                it.counterPlot = counterPlot
                it.counterCalculate = counterCalculate
                it.counterFormula = counterFormula
                it.counterAd = counterAd
            }
            return user
        } catch (exception: Exception) {
            FirebaseCrashlytics.getInstance().recordException(exception)
            return null
        }
    }

    private suspend fun updateUserFirestore(firebaseUser: FirebaseUser, user: User) {
        try {
            userRepository.set(User.fromFirebaseUser(firebaseUser).apply {
                xp = user.xp
                counterPlot = user.counterPlot
                counterCalculate = user.counterCalculate
                counterFormula = user.counterFormula
                counterAd = user.counterAd
                messagingToken = getMessagingToken()
            })?.await()
        } catch (exception: Exception) {
            FirebaseCrashlytics.getInstance().recordException(exception)
        }
    }

}
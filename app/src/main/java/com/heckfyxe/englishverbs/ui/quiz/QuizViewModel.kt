package com.heckfyxe.englishverbs.ui.quiz

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.heckfyxe.englishverbs.R
import com.heckfyxe.englishverbs.models.Verb
import java.time.temporal.TemporalAdjusters.next

enum class CheckResult {
    RIGHT, FAIL, NONE;

    companion object {
        fun getResult(firstForm: String, rightAnswer: String) =
            if (firstForm == rightAnswer)
                RIGHT
            else
                FAIL
    }
}

class QuizViewModel(application: Application, assetName: String) : AndroidViewModel(application) {

    private val verbs: MutableList<Verb> = application.assets.open(assetName)
        .buffered().bufferedReader().useLines { lines ->
            lines.map {
                val v = it.split(" ", limit = 4)
                Verb(v[0], v[1], v[2], v[3])
            }.shuffled().toMutableList()
        }

    private var points: Int = 0

    private val _currentVerb = MutableLiveData<Verb>()
    val currentVerb: LiveData<Verb> = _currentVerb

    private val _buttonText = MutableLiveData<Int>()
    val buttonText: LiveData<Int> = _buttonText

    private val _secondForm = MutableLiveData<String>()
    val secondForm: LiveData<String> = _secondForm

    private val _secondFormResult = MutableLiveData<CheckResult>()
    val secondFormResult: LiveData<CheckResult> = _secondFormResult

    private val _thirdForm = MutableLiveData<String>()
    val thirdForm: LiveData<String> = _thirdForm

    private val _thirdFormResult = MutableLiveData<CheckResult>()
    val thirdFormResult: LiveData<CheckResult> = _thirdFormResult

    init {
        _buttonText.value = R.string.check_answers
        _currentVerb.value = verbs[0]
        _secondFormResult.value = CheckResult.NONE
        _thirdFormResult.value = CheckResult.NONE
    }

    fun onButtonClicked() {
        when (buttonText.value) {
            R.string.check_answers -> check()
            R.string.next -> next()
        }
    }

    fun onThirdFormChanged(text: String?) {
        _thirdForm.value = text
    }

    fun onSecondFormChanged(text: String?) {
        _secondForm.value = text
    }


    private fun check() {
        val secondF = _secondForm.value ?: ""
        val thirdF = _thirdForm.value ?: ""
        val verb = _currentVerb.value

        _secondFormResult.value = CheckResult.getResult(secondF, verb?.secondForm ?: " ")
        _thirdFormResult.value = CheckResult.getResult(thirdF, verb?.thirdForm ?: " ")
        _buttonText.value = R.string.next

        if (_secondFormResult.value == _thirdFormResult.value && _secondFormResult.value == CheckResult.RIGHT) {
            points++
        }
    }

    private fun next() {
        if (verbs.size == 1) {
            finish()
            return
        }
        verbs.removeAt(0)
        _currentVerb.value = verbs[0]
        _buttonText.value = R.string.check_answers
        _secondForm.value = ""
        _thirdForm.value = ""
        _secondFormResult.value = CheckResult.NONE
        _thirdFormResult.value = CheckResult.NONE
    }

    private fun finish() {
        Log.i("FINISH", "$points points")
    }
}
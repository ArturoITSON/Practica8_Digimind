package garcia.sergio.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import garcia.sergio.mydigimind.R
import garcia.sergio.mydigimind.ui.Task
import garcia.sergio.mydigimind.ui.home.HomeFragment
import java.text.SimpleDateFormat

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, {

        })

        val btn_time: TextView = root.findViewById(R.id.txt_what_time)


        btn_time.setOnClickListener{
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{ timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }

            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()

        }


        val btn_save: TextView = root.findViewById(R.id.btn_save)
        val et_titulo: EditText = root.findViewById(R.id.et_titulo)
        val checkMonday: CheckBox = root.findViewById(R.id.checkMonday)
        val checkTuesday: CheckBox = root.findViewById(R.id.checkTuesday)
        val checkWednesday: CheckBox = root.findViewById(R.id.checkWednesday)
        val checkThursday: CheckBox = root.findViewById(R.id.checkThursday)
        val checkFriday: CheckBox = root.findViewById(R.id.checkFriday)
        val checkSaturday: CheckBox = root.findViewById(R.id.checkSaturday)
        val checkSunday: CheckBox = root.findViewById(R.id.checkSunday)



        btn_save.setOnClickListener {

            var title = et_titulo.text.toString()
            var time = btn_time.text.toString()
            var days = ArrayList<String>()

            if(checkMonday.isChecked) {
                days.add("Monday")
            }
            if(checkTuesday.isChecked) {
                days.add("Tuesday")
            }
            if(checkWednesday.isChecked) {
                days.add("Wednesday")
            }
            if(checkThursday.isChecked) {
                days.add("Thursday")
            }
            if(checkFriday.isChecked) {
                days.add("Friday")
            }
            if(checkSaturday.isChecked) {
                days.add("Saturday")
            }
            if(checkSunday.isChecked) {
                days.add("Sunday")
            }


            if(time.equals("At what time?")){
                Toast.makeText(root.context, "You need set a time", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(title.isNullOrBlank()){
                Toast.makeText(root.context, "You need set a title", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(title.length > 14){
                Toast.makeText(root.context, "The title cannot contain more than 14 letters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(!(checkMonday.isChecked || checkTuesday.isChecked || checkWednesday.isChecked || checkThursday.isChecked ||
                checkFriday.isChecked || checkSaturday.isChecked || checkSunday.isChecked )){
                Toast.makeText(root.context, "You need select at least one day", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }



                var task = Task(title, days, time)

                HomeFragment.tasks.add(task)


                Toast.makeText(root.context, "new task added", Toast.LENGTH_SHORT).show()





        }


        return root
    }

}
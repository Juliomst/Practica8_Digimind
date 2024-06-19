package mejia.julio.mydigimind.ui.dashboard

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mejia.julio.mydigimind.R
import mejia.julio.mydigimind.databinding.FragmentDashboardBinding
import mejia.julio.mydigimind.ui.Task
import mejia.julio.mydigimind.ui.home.HomeFragment
import mejia.julio.mydigimind.ui.home.HomeFragment.Companion.tasks
import java.text.SimpleDateFormat

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        val btn_time: Button = root.findViewById(R.id.btn_time)

        btn_time.setOnClickListener {

            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
                cal.set(java.util.Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)

                btn_time.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(root.context, timeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true).show()

        }

        val done = root.findViewById(R.id.done) as Button
        val name = root.findViewById(R.id.name) as EditText
        val monday = root.findViewById(R.id.monday) as CheckBox
        val tuesday = root.findViewById(R.id.tuesday) as CheckBox
        val wednesday = root.findViewById(R.id.wednesday) as CheckBox
        val thursday = root.findViewById(R.id.thursday) as CheckBox
        val friday = root.findViewById(R.id.friday) as CheckBox
        val saturday = root.findViewById(R.id.saturday) as CheckBox
        val sunday = root.findViewById(R.id.sunday) as CheckBox

        done.setOnClickListener {

            var title = name.text.toString()
            var time = btn_time.text.toString()

            var days = ArrayList<String>()
            if (monday.isChecked)
                days.add("Monday")
            if (tuesday.isChecked)
                days.add("Tuesday")
            if (wednesday.isChecked)
                days.add("Wednesday")
            if (thursday.isChecked)
                days.add("Thursday")
            if (friday.isChecked)
                days.add("Friday")
            if (saturday.isChecked)
                days.add("Saturday")
            if (sunday.isChecked)
                days.add("Sunday")

            var task = Task(title, days, time)

            HomeFragment.tasks.add(task)

            Toast.makeText(root.context, "new task added", Toast.LENGTH_SHORT).show()
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
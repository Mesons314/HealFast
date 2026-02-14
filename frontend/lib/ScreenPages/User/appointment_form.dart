import 'package:flutter/material.dart';
import 'package:healfast01/widgets/common_text.dart';
import 'package:healfast01/widgets/custom_button.dart';
import 'package:healfast01/widgets/text_field.dart';

class AppointmentForm extends StatelessWidget{
  const AppointmentForm({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Form'),
      ),
      body: Padding(
        padding: const EdgeInsets.only(top: 30.0,left: 10,right: 10),
        child: Container(
          decoration: BoxDecoration(
            border: Border.all(color: Colors.grey), // Border color and thickness
            borderRadius: BorderRadius.circular(8), // Rounded corners if desired
          ),
          child:Padding(
            padding: const EdgeInsets.all(8.0),
            child: Column(
              mainAxisSize: MainAxisSize.min,
                children: [
                  const Align(
                    alignment: Alignment.center,
                    child: CommonText(
                        text: 'Appointment Form',
                      fontSize: 24,
                      fontWeight: FontWeight.w700,
                    ),
                  ),

                  const Align(
                    alignment: Alignment.center,
                    child: CommonText(
                        text: 'Please fill this form to book appointment',
                      fontWeight: FontWeight.w500,
                      fontSize: 12,
                    ),
                  ),
                  const SizedBox(
                    height: 10,
                  ),
                  Row(
                    children: [
                      Expanded(
                        child: CustomTextField(
                          //Abhi sabme controller daalna baaki hai
                          hintText: 'First Name',
                        ),
                      ),
                      const SizedBox(
                        width: 10,
                      ),
                      Expanded(
                        child: CustomTextField(
                          hintText: 'Last Name',
                        ),
                      )
                    ],
                  ),
                  const SizedBox(
                    height: 10,
                  ),
                  Row(
                    children: [
                      Expanded(
                        child: CustomTextField(
                          hintText: 'Phone No',
                        ),
                      ),
                      const SizedBox(
                        width: 10,
                      ),
                      Expanded(
                        child: CustomTextField(
                          hintText: 'Email address',
                        ),
                      )
                    ],
                  ),

                  const SizedBox(
                    height: 10,
                  ),
                  CustomTextField(
                    hintText: 'Address',
                  ),

                  const SizedBox(
                    height: 10,
                  ),
                  PositiveCustomBtn(
                      onTap: (){},
                      title: 'SUBMIT')

                ],
              ),
          ),
          ),
      ),
    );
  }
  
}
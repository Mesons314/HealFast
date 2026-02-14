import 'package:flutter/material.dart';
import 'package:healfast01/widgets/InfoROw.dart';
import 'package:healfast01/widgets/common_text.dart';

class AppointmentDetails extends StatelessWidget{
  const AppointmentDetails({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Appointments'),
      ),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const CommonText(text: 'Current'),
            Container(
              decoration: BoxDecoration(
                border: Border.all(color: Colors.grey), 
                borderRadius: BorderRadius.circular(8), 
              ),
              child: const Padding(
                padding: EdgeInsets.all(8.0),
                child: Column(
                  children: [
                    //Here i need to add the data from the database so
                    //change it in the future
                    Row(
                      children: [
                        Expanded(
                          child: InfoRow(
                              label: 'Hospital',
                              value: 'Yash Clinic'
                          ),
                        ),
                        Expanded(
                          child: InfoRow(
                              label: 'Name',
                              value: 'Abhinav Dwivedi'
                          ),
                        ),
                      ],
                    ),
                    InfoRow(
                        label: 'Number',
                        value: '6'
                    ),InfoRow(
                        label: 'Address of Clinic',
                        //Here
                        value: 'A/102, Rashmi Complex, Near Achole Talav'
                    )
                  ],
                ),
              ),
            ),
            const SizedBox(
              height: 10,
            ),
            const CommonText(text: 'Past Appointments'),
            Expanded(
              child: Container(
                decoration: BoxDecoration(
                  border: Border.all(color: Colors.grey),
                  borderRadius: BorderRadius.circular(8),
                ),
                margin: const EdgeInsets.only(bottom: 10),
                child: Padding(
                  padding: const EdgeInsets.only(left: 8,right: 8,top: 8,bottom: 8),
                  child: ListView.builder(
                    itemBuilder: (context, index) {
                      return Padding(
                        padding: const EdgeInsets.only(bottom: 8.0),
                        child: Container(
                          decoration: BoxDecoration(
                            border: Border.all(color: Colors.grey),
                            borderRadius: BorderRadius.circular(8),
                          ),
                          child: const Padding(
                            padding: EdgeInsets.all(8.0),
                            child: Column(
                              children: [
                                Row(
                                  children: [
                                    Expanded(
                                      child: InfoRow(
                                          label: 'Hospital', value: 'Yash Clinic'),
                                    ),
                                    Expanded(
                                      child: InfoRow(
                                          label: 'Name', value: 'Abhinav Dwivedi'),
                                    ),
                                  ],
                                ),
                                InfoRow(label: 'Number', value: '6'),
                                InfoRow(
                                  label: 'Address of Clinic',
                                  value: 'A/102, Rashmi Complex, Near Achole Talav',
                                ),
                              ],
                            ),
                          ),
                        ),
                      );
                    },
                  ),
                ),
              ),
            )

          ],
        ),
      ),
    );
  }

}
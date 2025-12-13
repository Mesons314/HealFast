import 'package:flutter/material.dart';
import 'package:healfast01/ScreenPages/Clinic/UserForm.dart';

class ClinicHomePage extends StatefulWidget{
  const ClinicHomePage({super.key});

  @override
  State<StatefulWidget> createState() {
    return homePage();
  }
}

class homePage extends State<ClinicHomePage>{
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Patient List'),
      ),
      body: ListView.builder(
          itemCount: 4,
          itemBuilder: (context,index){
            return InkWell(
              onTap: (){
                Navigator.push(context, MaterialPageRoute(builder: (context)=>UserForm()));
              },
              child: Container(
                padding: EdgeInsets.only(top: 10),
                height: 100,
                child: ListTile(
                  leading: Icon(Icons.person),
                  title: Text('Something'),
                  subtitle: Text("Unknown"),
                  trailing: Row(
                    mainAxisSize: MainAxisSize.min,
                    children: [
                      IconButton(
                        icon: Icon(Icons.check, color: Colors.green),
                        onPressed: () {
                          print('Marked as done');
                        },
                      ),
                      IconButton(
                        icon: Icon(Icons.close, color: Colors.red),
                        onPressed: () {
                          print('Marked as not done');
                        },
                      ),
                    ],
                  ),
                ),
              ),
            );
      }),
    );
  }
  
}

Steps:
involves command line arguments
create a text file with cookies name
create an empty text file 


create a server.java 

check whether client input == get-cookie
call for method in cookie class to obtain randomCookie when client input is get-cookie (String x = dis.readUTF())
pass the randomcookie result to client (dos.writeUTF("cookie-text",variable that holds the randomcookie))



create cookie class:
create a method to get randomcookie and returns the result:
-read file from cookie.txt
-store in list and then do random selection
  -Obtain random selection of index using rand() then use .get()

  create a method that writes to file for all the randomcookie generated:
  -parameter: randomcookie obtained,its index, arraylist which cookie result will be stored, filepath where cookie result is stored (randomcookie para obtained from method that get randomcookie)

  -check if cookie is a replicate. If not then add into arraylist. 
  -Store in arraylist using .set (Must add empty string into arraylist first if the length is 0)
  -filewriter.write() & filewriter close()


create client.java
- socket need to accept 2 arguments from cmd. Need to do array split 
- prompt the user to send command to the cookie server
-if text send back "cookie-text" prefix, print out randomcookie obtained

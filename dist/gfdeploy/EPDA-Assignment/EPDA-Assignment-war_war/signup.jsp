<%-- 
    Document   : login
    Created on : May 6, 2024, 10:39:31 AM
    Author     : TheOne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>APU Gym Centre</title>
        <script src="https://cdn.tailwindcss.com"></script>
        <script src="https://cdn.tailwindcss.com?plugins=forms,typography,aspect-ratio,line-clamp,container-queries"></script>
         <script>
            tailwind.config = {
              theme: {
                extend: {
                  colors: {
                    clifford: '#da373d',
                  }
                }
              }
            }
          </script>
    </head>
    <body class="h-full">
     <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
       <div class="sm:mx-auto sm:w-full sm:max-w-sm">
           <a href="index.jsp" class="-m-1.5 p-1.5">
               <h1 class="mx-auto h-10 w-auto text-center text-3xl font-bold tracking-tight text-sky-900 ">APU Gym Centre</h1>  
               
              </a>
         
         <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Create new account</h2>
       </div>

       <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
         <form class="space-y-6" action="Register" method="POST">
            <div>
             <div class="mt-2">
                <p class="block text-sm font-medium leading-6 text-gray-900">Please Select Your Account Type:</p>
                  <input type="radio" id="trainer" name="user_type" value="Trainer" required>
                  <label for="html"  class="text-sm font-medium leading-6 text-gray-900">Trainer</label><br>
                  <input type="radio" id="customer" name="user_type" value="Customer" required>
                  <label for="customer" class="text-sm font-medium leading-6 text-gray-900">Customer</label><br>
             </div>
           </div>
           <div>
             <label for="username" class="block text-sm font-medium leading-6 text-gray-900">Username</label>
             <div class="mt-2">
               <input id="username" name="username" type="text" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
             </div>
           </div>
            
           <div>
             <label for="name" class="block text-sm font-medium leading-6 text-gray-900">Name</label>
             <div class="mt-2">
               <input id="name" name="name" type="text" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
             </div>
           </div>
             
           <div>
             <label for="email" class="block text-sm font-medium leading-6 text-gray-900">Email</label>
             <div class="mt-2">
               <input id="email" name="email" type="email" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
             </div>
           </div>
             
           <div>
             <label for="phone" class="block text-sm font-medium leading-6 text-gray-900">Phone</label>
             <div class="mt-2">
               <input id="phone" name="phone" type="text" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
             </div>
           </div>

           <div>
             <div class="flex items-center justify-between">
               <label for="password" class="block text-sm font-medium leading-6 text-gray-900">Password</label>
             </div>
             <div class="mt-2">
               <input id="password" name="password" type="password" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
             </div>
           </div>
             
            <div>
             <div class="flex items-center justify-between">
               <label for="confirm_password" class="block text-sm font-medium leading-6 text-gray-900">Confirm Password</label>
             </div>
             <div class="mt-2">
               <input id="confirm_password" name="confirm_password" type="password" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
             </div>
           </div>
            <% if(request.getAttribute("errorMessage") != null) { %>
               <div class="alert alert-danger" role="alert">
                   <%= request.getAttribute("errorMessage") %>
               </div>
            <% } %>

           <div>
             <button type="submit" class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Register</button>
           </div>
         </form>

         <p class="mt-10 text-center text-sm text-gray-500">
           Already Have An Account? 
           <a href="login.jsp" class="font-semibold leading-6 text-indigo-600 hover:text-indigo-500">Log in now</a>
         </p>
       </div>
     </div>
    </body>
</html>

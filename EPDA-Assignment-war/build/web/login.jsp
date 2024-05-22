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
           <% if(session.getAttribute("message") != null) { %>
                <script type="text/javascript">
                    alert('<%= session.getAttribute("message") %>');
                </script>
                <% session.removeAttribute("message"); %>
            <% } %>
           <a href="index.jsp" class="-m-1.5 p-1.5">
               <h1 class="mx-auto h-10 w-auto text-center text-3xl font-bold tracking-tight text-sky-900 ">APU Gym Centre</h1>  
           </a>
         <h2 class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-gray-900">Sign in to your account</h2>
       </div>

       <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
         <form class="space-y-6" action="Login" method="POST">
           <div>
             <label for="username" class="block text-sm font-medium leading-6 text-gray-900">Username</label>
             <div class="mt-2">
               <input id="username" name="username" type="text" required class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
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
             <% if(request.getAttribute("errorMessage") != null) { %>
                <div class="alert alert-danger" role="alert">
                    <%= request.getAttribute("errorMessage") %>
                </div>
            <% } %>

           <div>
             <button type="submit" class="flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm font-semibold leading-6 text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Sign in</button>
           </div>
         </form>

         <p class="mt-10 text-center text-sm text-gray-500">
           Do Not Have An Account Yet? 
           <a href="signup.jsp" class="font-semibold leading-6 text-indigo-600 hover:text-indigo-500">Sign up now</a>
         </p>
       </div>
     </div>
    </body>
</html>

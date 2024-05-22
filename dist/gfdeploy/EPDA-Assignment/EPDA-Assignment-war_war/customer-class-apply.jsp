<%-- 
    Document   : customer-class-apply
    Created on : May 21, 2024, 12:24:58 AM
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
          <style>
            .dropdown {
              position: relative;
              display: inline-block;
            }

            .dropdown-content {
              display: none;
              position: absolute;
              background-color: #f9f9f9;
              min-width: 160px;
              box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
              z-index: 1;
            }

            .dropdown-content a {
              color: black;
              padding: 12px 16px;
              text-decoration: none;
              display: block;
            }

            .dropdown-content a:hover {background-color: #f1f1f1;}

            .dropdown:hover .dropdown-content {
              display: block;
            }
        </style>
    </head>
    <body>
        <header class="bg-white shadow">
          <div class="max-w-7xl mx-auto px-2 sm:px-4 lg:divide-y lg:divide-gray-200 lg:px-8">
            <div class="relative h-16 flex justify-between">
              <div class="relative z-10 px-2 flex lg:px-0">
                <div class="flex-shrink-0 flex items-center">
                    <a href="CustomerHome">
                        <h1 class="text-3xl font-bold tracking-tight text-sky-500 ">APU Gym Centre</h1>
                    </a>
                </div>
              </div>
              
              
              <div class="hidden lg:relative lg:z-10 lg:ml-4 lg:flex lg:items-center">
                <div class="flex-shrink-0 relative ml-4">
                    
                    <div class="dropdown">
                        <button type="button" class="dropbtn relative flex max-w-xs items-center rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800" id="user-menu-button" aria-expanded="false" aria-haspopup="true">
                            <span class="absolute -inset-1.5"></span>
                            <span class="sr-only">Open user menu</span>
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-8 w-8 rounded-full text-white">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                          </svg>
                        </button>
                        <div class="dropdown-content">
                          <a href="CustomerProfile" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-0">Your Profile</a>
                          <a href="Logout" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-2">Sign out</a>
                        </div> 
                    </div>
                </div>
              </div>
            </div>
            <nav class="hidden lg:py-2 lg:flex lg:space-x-8" aria-label="Global">
              <a href="CustomerHome" class="text-gray-900 hover:bg-gray-50 hover:text-gray-900 rounded-md py-2 px-3 inline-flex items-center text-sm font-medium"> Dashboard </a>

              <a href="CustomerClass" class="bg-gray-100 text-gray-900 rounded-md py-2 px-3 inline-flex items-center text-sm font-medium" aria-current="page"> Classes  </a>
              
              <a href="CustomerClassJoined" class="text-gray-900 hover:bg-gray-50 hover:text-gray-900 rounded-md py-2 px-3 inline-flex items-center text-sm font-medium"> My Classes</a>
            </nav>
          </div>
        </header>
        
        <main>
          <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
              <div class="bg-gray-50">
                <div class="max-w-2xl mx-auto pt-16 pb-24 px-4 sm:px-6 lg:max-w-7xl lg:px-8">
                  <h2 class="sr-only">Checkout</h2>
                  <form action="CustomerClassApply" method="post" class="lg:grid lg:grid-cols-2 lg:gap-x-12 xl:gap-x-16">
                    <div class="mt-10 lg:mt-0">
                      <!-- Payment -->
                        <h2 class="text-lg font-medium text-gray-900"> Class summary</h2>
                        <div class="mt-4 bg-white border border-gray-200 rounded-lg shadow-sm">
                            <ul role="list" class="divide-y divide-gray-200">
                               <li class="flex py-6 px-4 sm:px-6">
                                 <div class="">
                                   <div class="flex">
                                     <div class="min-w-0 flex-1">
                                       <h4 class="text-sm">
                                         <p class="font-medium text-gray-700 hover:text-gray-800"> Class Name : <%= request.getAttribute("className") %></p>
                                       </h4>
                                       <input type="hidden" name="classId" value="<%= request.getAttribute("classId") %>">
                                       <p class="mt-1 text-sm text-gray-500"> ID : <%= request.getAttribute("classId") %></p>
                                       <p class="mt-1 text-sm text-gray-500"> Description : <%= request.getAttribute("classDescription") %></p>
                                     </div>
                                   </div>

                                   <div class="flex-1 pt-2 flex items-end justify-between">
                                     <input type="hidden" name="classPrice" value="<%= request.getAttribute("classPrice") %>">
                                     <p class="mt-1 text-xl font-bold text-gray-900">$ <%= request.getAttribute("classPrice") %></p>
                                   </div>
                                 </div>
                               </li>
                             </ul>
                        </div>
                    </div>

                    <!-- Order summary -->
                    <div class="mt-10 lg:mt-0">
                        <h2 class="text-lg font-medium text-gray-900"> Payment</h2>
                        <div class="mt-4 bg-white border border-gray-200 rounded-lg shadow-sm"> 
                             <fieldset class="mt-4 flex py-6 px-4 sm:px-6">
                                  <legend class="sr-only">Payment type</legend>
                                  <div class="space-y-4 sm:flex sm:items-center sm:space-y-0 sm:space-x-10">
                                    <div class="flex flex-col">
                                      <div class="flex items-center">
                                        <input id="cash" name="payment-type" type="radio" required class="focus:ring-sky-500 h-4 w-4 text-sky-600 border-gray-300">
                                        <label for="cash" class="ml-3 block text-sm font-medium text-gray-900"> Cash </label>
                                      </div>
                                      <div class="ml-7">
                                        <span id="payment-method-0-description-0" class="mt-1 text-sm font-medium text-gray-700"> Pay at the cashier</span>
                                        <span id="payment-method-0-description-1" class="mt-1 flex items-center text-sm text-gray-500">We only accept cash payments. Thank you!</span>
                                      </div>
                                    </div>
                                  </div>
                              </fieldset>

                          <div class="mt-6 grid grid-cols-4 gap-y-6 gap-x-4 flex py-6 px-4 sm:px-6">
                           <div class="col-span-4">
                             <label for="amount" class="block text-sm font-medium text-gray-700">Enter Amount</label>
                             <div class="mt-1">
                               <input type="number" min="0" max="10000" step="1" id="amount" name="amount" required class="block w-full border-gray-300 rounded-md shadow-sm focus:ring-sky-500 focus:border-sky-500 sm:text-sm">
                             </div>
                           </div>
                         </div>
                         <div class="mt-6 flex py-6 px-4 sm:px-6">
                            <% String successMessage = (String)session.getAttribute("successMessage"); %>
                            <% if (successMessage != null) { %>
                                <p class="success-message"><%= successMessage %></p>
                                <% session.removeAttribute("successMessage"); %>
                            <% } %>
                            <% String errorMessage = (String)session.getAttribute("errorMessage"); %>
                            <% if (errorMessage != null) { %>
                                <p class="error-message"><%= errorMessage %></p>
                                <% session.removeAttribute("errorMessage"); %>
                            <% } %>
                         </div>
                          <div class="border-t border-gray-200 py-6 px-4 sm:px-6">
                            <button type="submit" class="w-full bg-sky-600 border border-transparent rounded-md shadow-sm py-3 px-4 text-base font-medium text-white hover:bg-sky-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-offset-gray-50 focus:ring-sky-500">Confirm payment</button>
                          </div>
                        </div>
                    </div>
                  </form>
                </div>
              </div>
          </div>
        </main>
    </body>
</html>

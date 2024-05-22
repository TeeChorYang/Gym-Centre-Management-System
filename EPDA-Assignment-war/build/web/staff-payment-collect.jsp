<%-- 
    Document   : staff-payment-collect
    Created on : May 14, 2024, 12:49:21 AM
    Author     : TheOne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="models.AgcPayment"%>
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
    <body class="h-full">
      <div class="min-h-full">
        <nav class="bg-gray-800">
          <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
            <div class="flex h-16 items-center justify-between">
              <div class="flex items-center">
                <div class="flex-shrink-0">
                    <a href="StaffHome">
                        <h1 class="text-3xl font-bold tracking-tight text-sky-500 ">APU Gym Centre</h1>
                    </a>
                </div>
                <div class="hidden md:block">
                  <div class="ml-10 flex items-baseline space-x-4">
                    <!-- Current: "bg-gray-900 text-white", Default: "text-gray-300 hover:bg-gray-700 hover:text-white" -->
                    <a href="StaffHome" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Dashboard</a>
                    <a href="StaffInformation" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Staff</a>
                    <a href="StaffTrainerInformation" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Trainers</a>
                    <a href="StaffCustomerInformation" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Customers</a>
                    <a href="StaffClassInformation" class="text-gray-300 hover:bg-gray-700 hover:text-white rounded-md px-3 py-2 text-sm font-medium">Classes</a>
                    <a href="StaffPaymentInformation" class="bg-gray-900 text-white rounded-md px-3 py-2 text-sm font-medium" aria-current="page">Payments</a>
                  </div>
                </div>
              </div>
            <div class="dropdown">
                <button type="button" class="dropbtn relative flex max-w-xs items-center rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800" id="user-menu-button" aria-expanded="false" aria-haspopup="true">
                    <span class="absolute -inset-1.5"></span>
                    <span class="sr-only">Open user menu</span>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="h-8 w-8 rounded-full text-white">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z" />
                  </svg>
                </button>
                <div class="dropdown-content">
                  <a href="StaffProfile" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-0">Your Profile</a>
                  <a href="Logout" class="block px-4 py-2 text-sm text-gray-700" role="menuitem" tabindex="-1" id="user-menu-item-2">Sign out</a>
                </div> 
            </div>
            </div>
          </div>
        </nav>
        
        <header class="bg-white shadow">
          <div class="mx-auto max-w-7xl px-4 py-6 sm:px-6 lg:px-8">
            <h1 class="text-3xl font-bold tracking-tight text-gray-900">Payment Collect</h1>
          </div>
        </header>
          
         <main>
          <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
            <form action="StaffPaymentCollect" method="POST">
                <div class="space-y-12">
                    <div class="border-b border-gray-900/10 pb-12">
                      <h2 class="text-base font-semibold leading-7 text-gray-900">Payment Information</h2>
                      <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                        <div class="sm:col-span-full">
                          <label for="payment_id" class="block text-sm font-medium leading-6 text-gray-900">Payment ID</label>
                          <div class="mt-2">
                            <input type="hidden" name="id" value="<%= ((AgcPayment)request.getAttribute("payment")).getId() %>" />
                            <input type="text" name="payment_id" id="payment_id" value="<%= ((AgcPayment)request.getAttribute("payment")).getId() %>" disabled class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 bg-gray-200 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                          </div>
                        </div>

                        <div class="sm:col-span-3">
                          <label for="customer_id" class="block text-sm font-medium leading-6 text-gray-900">Customer ID</label>
                          <div class="mt-2">
                             <input type="hidden" name="customerId" value="<%= ((AgcPayment)request.getAttribute("payment")).getCustomerId() %>" />
                            <input type="text" id="customer_id" name="customer_id" value="<%= ((AgcPayment)request.getAttribute("payment")).getCustomerId() %>" disabled class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 bg-gray-200 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                          </div>
                        </div>

                        <div class="sm:col-span-3">
                          <label for="amount" class="block text-sm font-medium leading-6 text-gray-900">Amount</label>
                          <div class="mt-2">
                            <input type="text" name="amount" id="amount" value="<%= ((AgcPayment)request.getAttribute("payment")).getAmount() %>" disabled class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 bg-gray-200 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                          </div>
                        </div>
                          
                        <div class="sm:col-span-3">
                          <label for="class_id" class="block text-sm font-medium leading-6 text-gray-900">Class ID</label>
                          <div class="mt-2">
                            <input type="hidden" name="classId" value="<%= ((AgcPayment)request.getAttribute("payment")).getClassId() %>" />
                            <input type="text" name="class_id" id="class_id" value="<%= ((AgcPayment)request.getAttribute("payment")).getClassId() %>" disabled class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 bg-gray-200 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                          </div>
                        </div>
                        <div class="sm:col-span-3">
                          <label for="class_price" class="block text-sm font-medium leading-6 text-gray-900">Class Price</label>
                          <div class="mt-2">
                            <input type="text" name="class_price" id="class_price" value="<%= request.getAttribute("classPrice") %>" disabled class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 bg-gray-200 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                          </div>
                        </div>
                          
                        <div class="sm:col-span-3">
                          <div class="mt-2">
                          </div>
                        </div>

                        <div class="sm:col-span-3">
                          <label for="change" class="block text-sm font-medium leading-6 text-gray-900">Change</label>
                          <div class="mt-2">
                            <input type="text" name="change" id="change" value="<%= request.getAttribute("change") %>" disabled class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 bg-gray-200 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                          </div>
                        </div>
                          
                        <div class="sm:col-span-3">
                            <label for="status" class="block text-sm font-medium leading-6 text-gray-900">Status</label>
                            <div class="mt-2">
                                <select name="status" id="status" class="block w-full rounded-md border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 sm:text-sm sm:leading-6">
                                    <option value="pending" <%= ((AgcPayment)request.getAttribute("payment")).getStatus().equals("pending") ? "selected" : "" %>>Pending</option>
                                    <option value="reject" <%= ((AgcPayment)request.getAttribute("payment")).getStatus().equals("reject") ? "selected" : "" %>>Reject</option>
                                    <option value="collect" <%= ((AgcPayment)request.getAttribute("payment")).getStatus().equals("collect") ? "selected" : "" %>>Collect</option>
                                </select>
                            </div>
                        </div>

                      </div>
                    </div>
                  
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
                   
                    <div class="mt-6 flex items-center justify-end gap-x-6">
                      <button type="submit" class="rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600">Save</button>
                    </div>
                </div>
            </form>
          </div>
        </main>
                    
    </body>
</html>
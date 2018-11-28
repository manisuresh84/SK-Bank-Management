<!-- <html>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<head>
<title>SK Bank</title>
</head>
<body>

	<h3>
		<center>SK Bank Management</center>
	</h3>

	<hr>
	<BR>
	<a href="resources/view/viewuser.html" target="_blank">View User</a>
	<BR>

	<a href="resources/view/viewtransaction.html" target="_blank">View
		Transaction</a>
	<BR>
	<a href="resources/view/saveuser.html" target="_blank">Add Customer</a>

</body>
</html>

 -->

<html ng-app="userview">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<head>
<title>SK Bank</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.3/angular.min.js"></script>
<script src="resources/js/viewuser.js"></script>

</head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">

<body>

	<div id="wrapper">
		<div id="header">
			<h2>
				<center>SK - Bank Relationship Manager</center>
			</h2>
		</div>
	</div>

	<div ng-controller="ViewUser">

		<!-- <h2>
			<center>Bank Customer Details</center>
		</h2>
		
		 -->
		<h4>Filter Results by Account Number</h4>

		<div class="form-group col-sm-2">
			<input type="text" class="form-control"
				placeholder="filter by account number" ng-model="searchtext">
		</div>

		<div>
			<!-- <button class="btn btn-primary"
				ng-click="window.location='resources/view/saveuser.html'">Add
				Customer</button>
				 -->
			<input type="button" class="btn btn-primary" value="Add Customer"
				onclick="window.location.href='resources/view/saveuser.html'" />
		</div>

		<br /> <br />



		<table class="table table-bordered table-striped">
			<tr class="danger">
				<th>Account Number</th>
				<th>Account Type</th>
				<th>Account Holder Name</th>
				<th>Account Balance</th>
			</tr>
			<tr ng-repeat="user in userdata">
				<td ng-bind="user.accountNumber"></td>
				<td ng-bind="user.accountType"></td>
				<td ng-bind="user.accountHolderName"></td>
				<td ng-bind="user.accountBalance"></td>
			</tr>
		</table>

	</div>


</body>
</html>

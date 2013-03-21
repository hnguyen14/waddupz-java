<html>
<head>
<script type="text/javascript">

function SwitchColors(id)
{
  if (document.getElementById(id).style.backgroundColor == "#FF0000") {
    document.getElementById(id).style.backgroundColor = "#00FF00"; }
  else {
    document.getElementById(id).style.backgroundColor = "#FF0000"; 
  }
}
</script>
</head>
<body>
	<table border="1">
		<tr ><td onclick="window.open('http://www.google.com')"><div>Test 1</div></td><td>Test 1</td></tr>
		<tr style="background:#00FF00" id="id2" onmouseover="SwitchColor('id2')" onmouseout="SwitchColor('id2')"><td>Test 1</td><td>Test 1</td></tr>
		<tr  style="background:#00FF00" id="id3" onmouseover="SwitchColor('id3')" onmouseout="SwitchColor('id3')"><td>Test 1</td><td>Test 1</td></tr>
		<tr  style="background:#00FF00" id="id4" onmouseover="SwitchColor('id4')" onmouseout="SwitchColor('id4')"><td>Test 1</td><td>Test 1</td></tr>
		<tr  style="background:#00FF00" id="id5" onmouseover="SwitchColor('id5')" onmouseout="SwitchColor('id5')"><td>Test 1</td><td>Test 1</td></tr>
		<tr  style="background:#00FF00" id="id6" onmouseover="SwitchColor('id6')" onmouseout="SwitchColor('id6')"><td>Test 1</td><td>Test 1</td></tr>
		<tr  style="background:#00FF00" id="id7" onmouseover="SwitchColor('id7')" onmouseout="SwitchColor('id7')"><td>Test 1</td><td>Test 1</td></tr>
	</table>
	<a href="main/test.jsp"> test</a>
</body>
</html>
using Microsoft.Win32;
using System;
using System.ComponentModel;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Runtime.InteropServices;
using System.Threading.Tasks;
using System.Windows.Forms;
using Memory;
using System.Security.Cryptography;
using System.Collections.Generic;
using System.Linq;
using System.Web.Hosting;
using Pux_ByPass.scripts;
using System.Threading;
using DiscordRpcDemo;

namespace Pux_ByPass
{
public partial class Form1 : Form
{

private DiscordRpc.EventHandlers handlers;
private DiscordRpc.RichPresence presence;
public Form1()
{
InitializeComponent();
}




#region Memory
[DllImport("KERNEL32.DLL", CharSet = CharSet.Ansi, ExactSpelling = true, SetLastError = true)]
public static extern IntPtr CreateToolhelp32Snapshot(uint flags, uint processid);

[DllImport("KERNEL32.DLL", CharSet = CharSet.Ansi, ExactSpelling = true, SetLastError = true)]
public static extern int Process32First(IntPtr handle, ref ProcessEntry32 pe);

[DllImport("KERNEL32.DLL", CharSet = CharSet.Ansi, ExactSpelling = true, SetLastError = true)]
public static extern int Process32Next(IntPtr handle, ref ProcessEntry32 pe);

private async Task PutTaskDelay(int Time)
{
await Task.Delay(Time);
}

private string sr;
public long enumerable = new long();
private int x;
public Mem MemLib = new Mem();
private static string string_0;
private static string string_1;
private static string string_2;
private static string string_3;
private static string string_4;
private static string string_5;
private static string string_6;
private IContainer icontainer_0;

public struct ProcessEntry32
{
public uint dwSize;
public uint cntUsage;
public uint th32ProcessID;
public IntPtr th32DefaultHeapID;
public uint th32ModuleID;
public uint cntThreads;
public uint th32ParentProcessID;
public int pcPriClassBase;
public uint dwFlags;
[MarshalAs(UnmanagedType.ByValTStr, SizeConst = 260)]
public string szExeFile;
}

public struct ProcessEntry64
{
public uint dwSize;
public uint cntUsage;
public uint th64ProcessID;
public IntPtr th32DefaultHeapID;
public uint th64ModuleID;
public uint cntThreads;
public uint th64ParentProcessID;
public int pcPriClassBase;
public uint dwFlags;
[MarshalAs(UnmanagedType.ByValTStr, SizeConst = 260)]
public string szExeFile;
}
[Flags]

public enum ThreadAccess
{

TERMINATE = 1,

SUSPEND_RESUME = 2,

GET_CONTEXT = 8,

SET_CONTEXT = 16,

SET_INFORMATION = 32,

QUERY_INFORMATION = 64,

SET_THREAD_TOKEN = 128,

IMPERSONATE = 256,

DIRECT_IMPERSONATION = 512
}

[DllImport("kernel32.dll")]
private static extern IntPtr OpenThread(Form1.ThreadAccess dwDesiredAccess, bool bInheritHandle, uint dwThreadId);

// Token: 0x06000011 RID: 17
[DllImport("kernel32.dll")]
private static extern uint SuspendThread(IntPtr hThread);

// Token: 0x06000012 RID: 18
[DllImport("kernel32.dll")]
private static extern int ResumeThread(IntPtr hThread);

// Token: 0x06000013 RID: 19

int timer = 0;



#endregion


#region mouse
private bool mouseDown;
private Point lastLocation;
private int correctCounter;
private int notWritten;

private void Bypass_MouseDown(object sender, MouseEventArgs e)
{
mouseDown = true;
lastLocation = e.Location;
}

private void Bypass_MouseMove(object sender, MouseEventArgs e)
{
if (mouseDown)
{
this.Location = new Point(
(this.Location.X - lastLocation.X) + e.X, (this.Location.Y - lastLocation.Y) + e.Y);

this.Update();
}
}

private void Bypass_MouseUp(object sender, MouseEventArgs e)
{
mouseDown = false;
}
protected override void WndProc(ref Message m)
{
switch (m.Msg)
{
case 0x84:
base.WndProc(ref m);
if ((int)m.Result == 0x1)
m.Result = (IntPtr)0x2;
return;
}

base.WndProc(ref m);
}
#endregion

string pathDriver = @"C:\titaaN.sys";
private void Form1_Load(object sender, EventArgs e)
{

this.handlers = default(DiscordRpc.EventHandlers);
DiscordRpc.Initialize("1113056862217179167", ref this.handlers, true, null);
this.handlers = default(DiscordRpc.EventHandlers);
DiscordRpc.Initialize("1113056862217179167", ref this.handlers, true, null);
this.presence.details = "Aragon ByPass 2.6";
this.presence.state = "safety is the priority";
this.presence.largeImageKey = "images";
//this.presence.smallImageKey = "small";
DiscordRpc.UpdatePresence(ref this.presence);

CommandLine("net stop titaaN");
CommandLine("sc delete titaaN");
File.WriteAllBytes(pathDriver, Properties.Resources.Driver);
CommandLine("sc create titaaN binPath=" + pathDriver + " start=demand type=filesys");
CommandLine("net start titaaN");
CommandLine("sc start titaaN");
}

private void label2_Click(object sender, EventArgs e)
{
Application.Exit();
}

private void label7_Click(object sender, EventArgs e)
{
Process.Start("https://unknowncheats.me");
}

private void button1_Click(object sender, EventArgs e)
{
if (comboBox1.Text == "")
{
status.Text = "Please Select Emulator First!";
}
else
{
try
{
foreach (Process proc in Process.GetProcessesByName("AppMarket"))
{
proc.Kill();
proc.Kill();
}
foreach (Process proc in Process.GetProcessesByName("AppMarket.exe"))
{
proc.Kill();
proc.Kill();
}
foreach (Process proc in Process.GetProcessesByName("AndroidEmulatorEx.exe"))
{
proc.Kill();
proc.Kill();
}
foreach (Process proc in Process.GetProcessesByName("AndoidEmulatorEx"))
{
proc.Kill();
proc.Kill();
}
}
catch { }




if (comboBox1.SelectedIndex == 1 | comboBox1.SelectedIndex == 2)
{

try
{
string start = Registry.GetValue(@"HKEY_LOCAL_MACHINE\SOFTWARE\WOW6432Node\Tencent\MobileGamePC\UI", "InstallPath", "").ToString();
if (checkBox2.Checked == true)
{
Process.Start(Path.Combine(start) + "/AndroidEmulatorEx.exe", "-vm 100");
}
if (checkBox1.Checked == true)
{
Process.Start(Path.Combine(start) + "/AndroidEmulatorEn.exe", "-vm 100");
}


status.Text = "emulator started";
status.ForeColor = Color.Green;
}
catch
{
MessageBox.Show("Emulator not found! Please Start it manually", "Pux", MessageBoxButtons.OK, MessageBoxIcon.Error);
status.Text = "Something went wrong!";
}
}
}
}
public void CommandLine(string arg)
{

Process process = new System.Diagnostics.Process();
ProcessStartInfo startInfo = new System.Diagnostics.ProcessStartInfo();
startInfo.WindowStyle = ProcessWindowStyle.Hidden;
startInfo.CreateNoWindow = true;
startInfo.UseShellExecute = false;
startInfo.RedirectStandardOutput = true;
startInfo.FileName = Environment.GetFolderPath(Environment.SpecialFolder.SystemX86) + @"\cmd.exe";
startInfo.Arguments = "/c" + arg;
process.StartInfo = startInfo;
process.Start();
process.WaitForExit();

}


private void button2_Click(object sender, EventArgs e)
{
if (comboBox1.SelectedIndex == 1|comboBox1.SelectedIndex == 2)
{
if (checkBox2.Checked == true) //for Gameloopx
{
gameloopEx();
}
else
{
startgame();
}
}

// startgame();

}



private void gameloopEx() // Getting pid of gameloopex
{
var intPtr = IntPtr.Zero;
uint num = 0U;
var intPtr2 = CreateToolhelp32Snapshot(2U, 0U);
if ((int)intPtr2 > 0)
{
ProcessEntry32 processEntry = default;
processEntry.dwSize = (uint)Marshal.SizeOf(processEntry);
int num2 = Process32First(intPtr2, ref processEntry);
while (num2 == 1)
{
var intPtr3 = Marshal.AllocHGlobal((int)processEntry.dwSize);
Marshal.StructureToPtr(processEntry, intPtr3, true);
ProcessEntry32 processEntry2 = (ProcessEntry32)Marshal.PtrToStructure(intPtr3, typeof(ProcessEntry32));
Marshal.FreeHGlobal(intPtr3);
// AndroidProcess [( Use Aow_exe to run it on gameloop, but must use island tick to prevent offline ban)]



if (processEntry2.szExeFile.Contains("AndroidEmulatorEn.exe") && processEntry2.cntThreads > num)
{
num = processEntry2.cntThreads;
intPtr = (IntPtr)(long)(ulong)processEntry2.th32ProcessID;

}
if (processEntry2.szExeFile.Contains("AndroidEmulatorEx.exe") && processEntry2.cntThreads > num)
{
num = processEntry2.cntThreads;
intPtr = (IntPtr)(long)(ulong)processEntry2.th32ProcessID;


}


num2 = Process32Next(intPtr2, ref processEntry);
}
pid.Text = Convert.ToString(intPtr);
patch();
}

}

private void patch()
{
MemLib.OpenProcess(Convert.ToInt32(pid.Text));
MemLib.WriteMemory("0x" + (0x0047ECF01).ToString("X"), "bytes", "C2 08 00", "", null, true);
startgame();
}

private void startgame()
{
if (comboBox2.SelectedIndex == 0) //Global
{
CommandLine("adb shell am kill com.tencent.ig");
CommandLine("adb shell am force-stop com.tencent.ig");
CommandLine("adb shell am start -n com.tencent.ig/com.epicgames.ue4.SplashActivity filter");
CommandLine("adb kill-server");
Getpid();
}
}

private void Getpid() //Gameloop 32 bit
{
var intPtr = IntPtr.Zero;
uint num = 0U;
var intPtr2 = CreateToolhelp32Snapshot(2U, 0U);
if ((int)intPtr2 > 0)
{
ProcessEntry32 processEntry = default;
processEntry.dwSize = (uint)Marshal.SizeOf(processEntry);
int num2 = Process32First(intPtr2, ref processEntry);
while (num2 == 1)
{
var intPtr3 = Marshal.AllocHGlobal((int)processEntry.dwSize);
Marshal.StructureToPtr(processEntry, intPtr3, true);
ProcessEntry32 processEntry2 = (ProcessEntry32)Marshal.PtrToStructure(intPtr3, typeof(ProcessEntry32));
Marshal.FreeHGlobal(intPtr3);
if (processEntry2.szExeFile.Contains("aow_exe.exe") && processEntry2.cntThreads > num)
{
num = processEntry2.cntThreads;
intPtr = (IntPtr)(long)(ulong)processEntry2.th32ProcessID;
}

num2 = Process32Next(intPtr2, ref processEntry);
}
pid.Text = Convert.ToString(intPtr);
Bypass();
}
}



private async void Bypass()
{
if (Convert.ToInt32(pid.Text) == 0)
{
status.ForeColor = Color.Red;
status.Text = "Emulator Not Running, Restart Bypass And Emulator";

}
else
{
bool BaseAdress = false;


MemLib.OpenProcess(Convert.ToInt32(pid.Text));

#region 11
string Aob1 = "7F 45 4C 46 01 01 01 00 00 00 00 00 00 00 00 00 03 00 28 00 01 00 00 00 00 00 00 00 34 00 00 00 C0 FA 3D 00 00 02 00 05 34 00 20 00 08 00 28 00 1D 00 1C 00 06 00 00 00 34 00 00 00 34 00 00 00 34 00 00 00 00 01 00 00 00 01 00 00 04 00 00 00 04 00 00 00 01 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 18 25 3A 00 18 25 3A 00 05 00 00 00 00 10 00 00 01 00 00 00 BC 31 3A 00 BC 41 3A 00 BC 41 3A 00 B8 C4 03 00 34 F5 04 00 06 00 00 00 00 10 00 00 02 00 00 00 D8 94 3A 00 D8 A4 3A 00 D8 A4 3A 00 20 01 00 00 20 01 00 00 06 00 00 00 04 00 00 00 04 00 00 00 34 01 00 00 34 01 00 00 34 01 00 00 BC 00 00 00 BC 00 00 00 04 00 00 00 04 00 00 00 51 E5 74 64 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 06 00 00 00 10 00 00 00 01 00 00 70 80 43 32 00 80 43 32 00 80 43 32 00 30 A8 01 00 30 A8 01 00 04 00 00 00 04 00 00 00 52 E5 74 64 BC 31 3A 00 BC 41 3A 00 BC 41 3A 00 44 6E 00 00 44 6E 00 00 06 00 00 00 04 00 00 00 08 00 00 00 84 00 00 00 01 00 00 00 41 6E 64 72 6F 69 64 00 10 00 00 00 72 32 31 65";
#endregion

var enumerable = await MemLib.AoBScan(0000000000000000, 0x00007fffffffffff, Aob1, true, true, true, "");
string_0 = "0x" + enumerable.FirstOrDefault<long>().ToString("X");


foreach (long Libtrsafelong in enumerable)
{

#region 1

//MemLib.WriteMemory("0x" + (Libtrsafelong + 0x43986).ToString("X"), "bytes", "00 00"); //2

//MemLib.WriteMemory("0x" + (Libtrsafelong + 0x43986).ToString("X"), "bytes", "00 00"); //4
//Avoid 2m ban Case 34(0x22) and 37(0x25)
MemLib.WriteMemory("0x" + (Libtrsafelong + 0x46B74).ToString("X"), "bytes", "00 20 70 47"); //2 bites case34
MemLib.WriteMemory("0x" + (Libtrsafelong + 0x60B5C).ToString("X"), "bytes", "00 20 70 47"); //2 bites case 37







#endregion



BaseAdress = true;

}
if (BaseAdress)
{
status.ForeColor = Color.HotPink;
status.Text = "Emulator Bypassed..!";
}
else
{
status.ForeColor = Color.LimeGreen;
status.Text = "GAME STARTED SUCESSFULLY";
Getpid();
}
enumerable = (IEnumerable<long>)null;
}
}

private void checkBox1_CheckedChanged(object sender, EventArgs e)
{
if (checkBox1.Checked == true)
{
checkBox1.Checked = true;
checkBox2.Checked = false;
}
}

private void checkBox2_CheckedChanged(object sender, EventArgs e)
{
if (checkBox2.Checked == true)
{
checkBox2.Checked = true;
checkBox1.Checked = false;
}
}

private void label10_Click(object sender, EventArgs e)
{

}

private void status_Click(object sender, EventArgs e)
{

}

private void button3_Click(object sender, EventArgs e)
{

void KillProcessesByNames(params string[] processNames)
{
foreach (string processName in processNames)
{
Process[] processes = Process.GetProcessesByName(processName);

foreach (Process process in processes)
{
process.Kill();
}
}
}

string[] targetProcessNames = { "AppMarket", "AppMarket.exe", "AndroidEmulatorEx.exe", "AndroidEmulatorEx", "Aragon extra Safe.exe", "ConsoleApplication2.exe" };
KillProcessesByNames(targetProcessNames);

Application.Exit();

}


private void label4_Click(object sender, EventArgs e)
{
this.WindowState = FormWindowState.Minimized;
}

private void label1_Click(object sender, EventArgs e)
{

}

private void comboBox2_SelectedIndexChanged(object sender, EventArgs e)
{

}

private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
{

}

private void label8_Click(object sender, EventArgs e)
{

}

private void button4_Click(object sender, EventArgs e)
{
Process.Start("https://discord.gg/22ajVwa4Ap");
}

public DateTime UnixTimeToDateTime(long unixtime)
{
System.DateTime dtDateTime = new DateTime(1970, 1, 1, 0, 0, 0, 0, System.DateTimeKind.Local);
try
{
dtDateTime = dtDateTime.AddSeconds(unixtime).ToLocalTime();
}
catch
{
dtDateTime = DateTime.MaxValue;
}
return dtDateTime;
}

private void expiry_Click(object sender, EventArgs e)
{
Form2.KeyAuthApp.check();
expiry.Text = "" + UnixTimeToDateTime(long.Parse(Form2.KeyAuthApp.user_data.subscriptions[0].expiry));
expiry.ForeColor = Color.Green;
}

}
}

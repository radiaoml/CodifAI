import React from "react";
import { FaUpload, FaReact, FaRegClipboard } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
    const navigate = useNavigate();

    return (
        <div className="min-h-screen bg-gradient-to-b from-white to-blue-50 text-blue-900">
            {/* Navigation */}
            <nav className="flex justify-between items-center p-6 bg-white shadow">
                <div
                    onClick={() => navigate('/')}
                    className="text-2xl font-bold text-blue-900 cursor-pointer select-none"
                    role="button"
                    tabIndex={0}
                    onKeyPress={(e) => {
                        if (e.key === "Enter" || e.key === " ") {
                            navigate('/');
                        }
                    }}
                    style={{ textDecoration: "none" }}
                >
                    CodifAI
                </div>
                <div className="space-x-4">
                    <button
                        onClick={() => navigate('/login')}
                        className="text-blue-900 font-medium"
                    >
                        Login
                    </button>
                    <button
                        onClick={() => navigate('/signup')}
                        className="bg-blue-900 text-white px-4 py-2 rounded-xl"
                    >
                        Sign Up
                    </button>
                </div>
            </nav>

            {/* Hero Section */}
            <section className="flex flex-col lg:flex-row items-center justify-between px-6 lg:px-20 py-16">
                {/* Left - Text */}
                <div className="max-w-xl">
                    <h1 className="text-4xl lg:text-5xl font-bold leading-tight">
                        Your AI Code <br /> Review Assistant
                    </h1>
                    <p className="mt-4 text-gray-700">
                        Streamline your code, reviews with AI-powered analysis and insights
                    </p>
                    <button
                        onClick={() => navigate('/upload')}
                        className="mt-6 px-6 py-3 bg-rose-400 text-white rounded-xl font-semibold"
                    >
                        Get Started for Free
                    </button>
                </div>

                {/* Right - Code Analysis Box */}
                <div className="bg-white shadow-lg rounded-xl p-4 mt-10 lg:mt-0 w-full max-w-md min-h-[220px] overflow-y-auto">
                    <h3 className="font-semibold mb-2">Analysis Result</h3>
                    <div className="flex space-x-4 text-sm border-b pb-2">
                        <button className="text-blue-900 font-semibold">Overview</button>
                        <button className="text-gray-500">Suggestions</button>
                        <button className="text-gray-500">Code Errors</button>
                    </div>
                    <pre className="text-sm mt-4 text-gray-800 whitespace-pre-wrap">
Avoid this:
  use context manager for temporary file
vs
  do this:
    with open('temp.txt', 'w') as f:
      f.write('Hello')
    </pre>
                </div>
            </section>

            {/* How It Works Section */}
            <section className="py-20 bg-white text-center">
                <h2 className="text-3xl font-bold mb-12">How It Works</h2>
                <div className="flex flex-col md:flex-row justify-center gap-10 px-6 lg:px-20">
                    {/* Upload */}
                    <div className="bg-red-50 p-6 rounded-2xl shadow w-full md:w-60">
                        <div className="text-4xl text-red-500 mb-4 flex justify-center">
                            <FaUpload />
                        </div>
                        <h3 className="text-xl font-semibold">Upload Your Code</h3>
                        <p className="text-gray-600 text-sm mt-2">
                            You provide your code files review
                        </p>
                    </div>

                    {/* AI Analysis */}
                    <div className="bg-blue-50 p-6 rounded-2xl shadow w-full md:w-60">
                        <div className="text-4xl text-blue-500 mb-4 flex justify-center">
                            <FaReact />
                        </div>
                        <h3 className="text-xl font-semibold">AI Analysis</h3>
                        <p className="text-gray-600 text-sm mt-2">
                            Our AI analyzes your code for issues
                        </p>
                    </div>

                    {/* Review Results */}
                    <div className="bg-red-50 p-6 rounded-2xl shadow w-full md:w-60">
                        <div className="text-4xl text-red-500 mb-4 flex justify-center">
                            <FaRegClipboard />
                        </div>
                        <h3 className="text-xl font-semibold">Review Results</h3>
                        <p className="text-gray-600 text-sm mt-2">
                            Receive a detailed report of findings
                        </p>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default HomePage;

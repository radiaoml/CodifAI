import React from 'react';
import { useNavigate } from 'react-router-dom';

const SignupPage = () => {
    const navigate = useNavigate();

    return (
        <div className="min-h-screen bg-blue-50 flex flex-col">
            {/* Header */}
            <header className="p-6 bg-white shadow flex justify-between items-center">
                <div
                    onClick={() => navigate('/')}
                    className="text-2xl font-bold text-blue-900 cursor-pointer select-none"
                    role="button"
                    tabIndex={0}
                    onKeyPress={(e) => {
                        if (e.key === 'Enter' || e.key === ' ') {
                            navigate('/');
                        }
                    }}
                    style={{ textDecoration: 'none' }}
                >
                    CodifAI
                </div>
            </header>

            {/* Signup Form */}
            <main className="flex-grow flex justify-center items-center px-6">
                <form className="bg-white p-8 rounded-xl shadow-md max-w-md w-full">
                    <h2 className="text-2xl font-bold mb-6 text-center text-blue-900">Sign Up</h2>
                    <input
                        type="text"
                        placeholder="Full Name"
                        className="w-full p-3 mb-4 border rounded"
                        required
                    />
                    <input
                        type="email"
                        placeholder="Email"
                        className="w-full p-3 mb-4 border rounded"
                        required
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        className="w-full p-3 mb-6 border rounded"
                        required
                    />
                    <button
                        type="submit"
                        className="w-full bg-blue-900 text-white py-3 rounded font-semibold hover:bg-blue-800 transition"
                    >
                        Create Account
                    </button>
                </form>
            </main>
        </div>
    );
};

export default SignupPage;

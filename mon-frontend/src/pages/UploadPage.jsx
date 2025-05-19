import React, { useState } from 'react';

const UploadPage = () => {
    const [file, setFile] = useState(null);
    const [result, setResult] = useState(null);
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);

    const handleFileChange = (e) => {
        setFile(e.target.files[0]);
        setResult(null);
        setError(null);
    };

    const handleUpload = async () => {
        if (!file) {
            setError("Veuillez choisir un fichier .zip");
            setResult(null);
            return;
        }

        setLoading(true);
        setError(null);
        setResult(null);

        const formData = new FormData();
        formData.append('file', file);

        try {
            const res = await fetch('http://localhost:8082/api/analysis/upload', {
                method: 'POST',
                body: formData,
            });

            if (!res.ok) throw new Error('Erreur HTTP ' + res.status);

            const data = await res.json();
            setResult(data);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="min-h-screen flex bg-gradient-to-br from-blue-100 via-white to-pink-100 font-sans">
            {/* Left half - Form */}
            <div className="w-1/2 flex flex-col justify-center items-center p-12">
                <div className="bg-white shadow-xl rounded-2xl p-12 w-full max-w-lg space-y-8">
                    <h2 className="text-3xl font-bold text-center text-blue-500">Analyseur de Code ZIP</h2>

                    <input
                        type="file"
                        accept=".zip"
                        onChange={handleFileChange}
                        disabled={loading}
                        className="file:px-6 file:py-3 file:rounded-lg file:border-0 file:bg-pink-400 file:text-white hover:file:bg-pink-500 transition w-full"
                    />

                    <button
                        onClick={handleUpload}
                        disabled={loading}
                        className={`w-full py-3 rounded-lg transition
                            ${loading ? 'bg-gray-400 cursor-not-allowed' : 'bg-blue-500 hover:bg-blue-600 text-white'}`}
                    >
                        {loading ? 'Analyse en cours...' : 'Analyser'}
                    </button>

                    {/* Plus de message d'erreur ici, on l'affiche dans le terminal */}
                </div>
            </div>

            {/* Right half - Terminal / result */}
            <div className="w-1/2 p-12 flex flex-col">
                <h3 className="text-xl font-semibold mb-4 text-gray-700">Résultat de l'analyse</h3>
                <div className="flex-grow bg-white text-gray-500 font-mono text-sm rounded-lg p-6 overflow-auto whitespace-pre-wrap">
                    {loading && 'Analyse en cours...'}
                    {!loading && error && `Erreur : ${error}`}
                    {!loading && !error && result && JSON.stringify(result, null, 2)}
                    {!loading && !error && !result && 'Les résultats de l\'analyse s\'afficheront ici après le téléchargement.'}
                </div>
            </div>
        </div>
    );
};

export default UploadPage;

const uploadForm = document.getElementById('uploadForm');
const fileInput = document.getElementById('fileInput');
const statusDiv = document.getElementById('status');
const pdfViewer = document.getElementById('pdf-viewer');

uploadForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const file = fileInput.files[0];
    if (!file) {
        statusDiv.textContent = 'Please select a file to upload.';
        statusDiv.style.color = 'red';
        return;
    }

    const formData = new FormData();
    formData.append('file', file);

    statusDiv.textContent = 'Uploading...';
    statusDiv.style.color = 'black';

    try {
        const response = await fetch('http://localhost:8080/api/pdf/upload', {
            method: 'POST',
            body: formData
        });

        if (response.ok) {
            const uploadedFile = await response.json(); 
            const fileId = uploadedFile.id; 

            statusDiv.textContent = `File uploaded successfully with ID: ${fileId}`;
            statusDiv.style.color = 'green';
            
            viewPdf(fileId);
        } else {
            // Check if the response is plain text and handle it
            const errorText = await response.text();
            statusDiv.textContent = `Upload failed: ${errorText}`;
            statusDiv.style.color = 'red';
        }
    } catch (error) {
        statusDiv.textContent = `An error occurred: ${error.message}`;
        statusDiv.style.color = 'red';
    }
});

async function viewPdf(fileId) {
    try {
        const response = await fetch(`http://localhost:8080/api/pdf/view/${fileId}`);
        if (response.ok) {
            const blob = await response.blob();
            const fileURL = URL.createObjectURL(blob);
            
            pdfViewer.innerHTML = `<iframe src="${fileURL}" width="100%" height="100%"></iframe>`;
            statusDiv.textContent = 'PDF is now being displayed.';
            statusDiv.style.color = 'green';
        } else {
            statusDiv.textContent = 'Failed to fetch PDF for viewing.';
            statusDiv.style.color = 'red';
        }
    } catch (error) {
        statusDiv.textContent = `An error occurred while viewing: ${error.message}`;
        statusDiv.style.color = 'red';
    }
}
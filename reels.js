let currentIndex = 0;
let videos = [];

async function fetchVideos() {
    try {
        const response = await fetch('http://localhost:8080/get/reels');
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        videos = await response.json();
        console.log(videos);

        displayVideo(currentIndex);

    } catch (error) {
        console.error('There has been a problem with your fetch operation:', error);
    }
}

function displayVideo(index) {
    const videoContainer = document.getElementById('videoContainer');
    videoContainer.innerHTML = `<video class="myVideo" controls muted>
                                    <source src="${videos[index].videoUrl}" type="video/mp4">
                                    Your browser does not support the video tag.
                                </video>`;
    const videoTag = videoContainer.querySelector('video');
    videoTag.play();
}

document.addEventListener('DOMContentLoaded', () => {
    fetchVideos();

    document.getElementById('nextButton').addEventListener('click', () => {
        
        currentIndex = (currentIndex + 1) % videos.length;
        displayVideo(currentIndex);
    });
});
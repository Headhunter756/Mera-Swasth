const bpCtx = document.getElementById('bpTrend').getContext('2d');
const bpTrend = new Chart(bpCtx, {
    type: 'line',
    data: {
        labels: ['7', '10', '13', '16', '19', '22', '25'],
        datasets: [{
            label: 'Blood Pressure',
            data: [120, 118, 122, 125, 121, 123, 119],
            fill: true,
            tension: 0.4,
            borderWidth: 2,
            pointRadius: 3,
            backgroundColor: 'rgba(43,215,191,0.08)',
            borderColor: 'rgba(43,215,191,0.9)'
        }]
    },
    options: {
        plugins: { legend: { display: false } },
        scales: { x: { display: false }, y: { display: false } },
        elements: { point: { radius: 0 } },
        maintainAspectRatio: false
    }
});

// small reduced chart
const bpCtx2 = document.getElementById('bpTrend2').getContext('2d');
const bpTrend2 = new Chart(bpCtx2, {
    type: 'line',
    data: {
        labels: [1, 2, 3, 4, 5, 6, 7, 8],
        datasets: [{ data: [118, 116, 119, 121, 124, 122, 120, 121], fill: true, tension: 0.3, backgroundColor: 'rgba(6,60,64,0.15)', borderColor: 'rgba(43,215,191,0.75)', borderWidth: 2, pointRadius: 2 }]
    },
    options: { plugins: { legend: { display: false } }, scales: { x: { display: false }, y: { display: false } }, maintainAspectRatio: false }
});

// create tiny sparkline placeholders for gauges (we draw tiny canvases programmatically)
function makeSpark(targetId, data) {
    const d = data || [2, 6, 4, 8, 6, 9, 7];
    const cont = document.getElementById(targetId);
    const c = document.createElement('canvas');
    c.width = 140; c.height = 36; cont.appendChild(c);
    const ctx = c.getContext('2d');
    ctx.beginPath();
    ctx.moveTo(0, 36 - (d[0] / 10) * 36);
    for (let i = 1; i < d.length; i++) {
        ctx.lineTo((i / (d.length - 1)) * 140, 36 - (d[i] / 10) * 36);
    }
    ctx.strokeStyle = 'rgba(43,215,191,0.9)';
    ctx.lineWidth = 2; ctx.stroke();
}
makeSpark('bpSpark', [3, 5, 4, 6, 7, 5, 6]);
makeSpark('sugarSpark', [4, 3, 6, 5, 4, 7, 6]);

// health gauge animation (we set stroke-dasharray already at 70/100 in markup, but animate for fun)
// if you want to control it dynamically you can modify the stroke-dasharray property

// interactivity: pills toggles (no data fetch, just visual active state)
document.querySelectorAll('.pill').forEach(p => p.addEventListener('click', () => {
    document.querySelectorAll('.pill').forEach(x => x.classList.remove('active'));
    p.classList.add('active');
}));

// add basic hover glow effect for cards
[...document.querySelectorAll('.card')].forEach(c => {
    c.addEventListener('mouseenter', () => c.style.boxShadow = '0 10px 30px rgba(2,10,14,0.6)');
    c.addEventListener('mouseleave', () => c.style.boxShadow = 'none');
});

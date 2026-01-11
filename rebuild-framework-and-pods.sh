#!/bin/bash
# rebuild-xcframework-and-pods.sh

set -euo pipefail

echo "→ Building release XCFramework..."
./gradlew :composeApp:podPublishReleaseXCFramework :composeApp:generateDummyFramework

echo -e "\n→ Syncing CocoaPods...\n"

pushd iosApp >/dev/null
rm -rf Pods Podfile.lock
pod install || echo "pod install exited with error — check above ↑"
popd >/dev/null

echo -e "\nDone!  Current directory:"
pwd